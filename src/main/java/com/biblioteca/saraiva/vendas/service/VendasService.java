package com.biblioteca.saraiva.vendas.service;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import com.biblioteca.saraiva.vendas.dto.ItemRequest;
import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.enums.EnumVenda;
import com.biblioteca.saraiva.vendas.model.ItemVenda;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VendasService {

    private final LivrosRepository livrosRepository;
    private final VendasRepository vendasRepository;

    public VendasService(LivrosRepository livrosRepository,
                         VendasRepository vendasRepository) {
        this.livrosRepository = livrosRepository;
        this.vendasRepository = vendasRepository;
    }




    public VendasModel buscarPorId(Long id) {
        VendasModel vendas = vendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrado"));

        return vendas;
    }

    public List<VendasModel> buscarPorStatus(EnumVenda status){
        return vendasRepository.findByStatus(status);
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

        venda.setStatus(EnumVenda.CANCELADA);
    }





    @Transactional
    public VendasModel vender(VendaRequest request) {

        //Pegar todos os IDs de livros p depois buscar de uma vez só
        List<Long> idsLivros = request.getItens().stream() //".stream()" ele abre um fluxo dos itens da requisição
                .map(ItemRequest::getLivroId) //ele fala para extrair só o ID
                .toList(); //fecha o fluxo com uma lista

        //Aqui ele busca todos os livros
        List<LivrosModel> livrosNoBanco = livrosRepository.findAllById(idsLivros);

        //Usar um map pra ficar mais rapido o acesso dos IDs no loop
        Map<Long, LivrosModel> mapaLivros = livrosNoBanco.stream() //usando a query de cima
                .collect(Collectors.toMap(LivrosModel::getId, livro -> livro));


        VendasModel venda = new VendasModel();
        venda.setFormaPagamento(request.getFormaPagamento());

        List<ItemVenda> itensVenda = new ArrayList<>();
        BigDecimal totalVenda = BigDecimal.ZERO;

        // Processando os itens
        for (ItemRequest itemReq : request.getItens()) {

            LivrosModel livro = mapaLivros.get(itemReq.getLivroId());


            if (livro == null) {
                throw new RuntimeException("Livro ID" + itemReq.getLivroId() + " não encontrado");

            }

            if (livro.getQuantidade() < itemReq.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o livro: " + livro.getTitulo());
            }

            //Atualiza o estoque
            livro.setQuantidade(livro.getQuantidade() - itemReq.getQuantidade());

            //Criar item de benda
            ItemVenda item = new ItemVenda();
            item.setLivro(livro);
            item.setVenda(venda);
            item.setQuantidade(itemReq.getQuantidade());
            item.setPrecoUnitario(livro.getPreco());

            BigDecimal subtotal = livro.getPreco().multiply(BigDecimal.valueOf(itemReq.getQuantidade()));
            totalVenda = totalVenda.add(subtotal);

            itensVenda.add(item);



        }

        venda.setItens(itensVenda);
        venda.setValorTotal(totalVenda);
        venda.setStatus(EnumVenda.REALIZADA);


        //Salva tudo atualizado de uma vez só
        livrosRepository.saveAll(livrosNoBanco);

        // Salva a venda
        return vendasRepository.save(venda);
    }
}