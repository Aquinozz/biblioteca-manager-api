package com.biblioteca.saraiva.vendas.service;

import com.biblioteca.saraiva.dados.model.DadosModel;
import com.biblioteca.saraiva.dados.repository.DadosRepository;
import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import com.biblioteca.saraiva.vendas.dto.ItemRequest;
import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.model.ItemVenda;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendasService {

    private final LivrosRepository livrosRepository;
    private final VendasRepository vendasRepository;
    private final DadosRepository dadosRepository;

    public VendasService(LivrosRepository livrosRepository,
                         VendasRepository vendasRepository,
                         DadosRepository dadosRepository) {
        this.livrosRepository = livrosRepository;
        this.vendasRepository = vendasRepository;
        this.dadosRepository = dadosRepository;
    }





    public VendasModel buscarPorId(Long id) {
        VendasModel vendas = vendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrado"));

        return vendas;
    }

    @Transactional
    public void cancelarVenda(Long id){
        VendasModel venda = vendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrado"));

        for (ItemVenda item : venda.getItens()) {
            LivrosModel livro = item.getLivro();

            livro.setQuantidade(
                    livro.getQuantidade() + item.getQuantidade()
            );
        }

        vendasRepository.delete(venda);
    }



    public List<VendasModel> listarVendas() {
        return vendasRepository.findAll();
    }


    @Transactional
    public VendasModel vender(VendaRequest request) {

        // Cria uma nova venda
        VendasModel venda = new VendasModel();

        // Define a forma de pagamento recebida no request
        venda.setFormaPagamento(request.getFormaPagamento());

        // Lista que vai armazenar os itens da venda
        List<ItemVenda> itens = new ArrayList<>();

        // Variável para acumular o valor total da venda
        BigDecimal total = BigDecimal.ZERO;

        // Percorre cada item enviado no request
        for (ItemRequest itemReq : request.getItens()) {

            // Busca o livro no banco pelo ID
            LivrosModel livro = livrosRepository.findById(itemReq.getLivroId())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

            // Verifica se há quantidade suficiente em estoque
            if (livro.getQuantidade() < itemReq.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente");
            }

            // Cria um novo item de venda
            ItemVenda item = new ItemVenda();

            // Associa o item ao livro
            item.setLivro(livro);

            // Associa o item à venda
            item.setVenda(venda);

            // Define a quantidade vendida
            item.setQuantidade(itemReq.getQuantidade());

            // Define o preço unitário do livro no momento da venda
            item.setPrecoUnitario(livro.getPreco());

            // Calcula o subtotal (preço * quantidade)
            BigDecimal subtotal = livro.getPreco()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantidade()));

            // Soma o subtotal ao total da venda
            total = total.add(subtotal);


            // Atualiza o estoque do livro
            livro.setQuantidade(livro.getQuantidade() - itemReq.getQuantidade());
            livrosRepository.save(livro);

            // Adiciona o item à lista de itens da venda
            itens.add(item);
        }

        // Define os itens na venda
        venda.setItens(itens);

        // Define o valor total da venda
        venda.setValorTotal(total);

        // Salva a venda no banco
        vendasRepository.save(venda);

        // Retorna a venda criada
        return venda;
    }
}