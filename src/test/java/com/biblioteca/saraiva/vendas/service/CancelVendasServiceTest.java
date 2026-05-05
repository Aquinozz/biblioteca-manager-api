package com.biblioteca.saraiva.vendas.service;

import com.biblioteca.saraiva.livros.enums.EnumLivro;
import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import com.biblioteca.saraiva.vendas.dto.ItemRequest;
import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.enums.EnumPagamentoVenda;
import com.biblioteca.saraiva.vendas.enums.EnumStatusVenda;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CancelVendasServiceTest {



    @Autowired
    VendasService vendasService;

    @Autowired
    LivrosRepository livrosRepository;


    @Test
    void cancelarVenda() {

        VendasModel venda = vender();

        vendasService.cancelarVenda(venda.getId());

        VendasModel vendaAtualizada = vendasService.buscarPorId(venda.getId());

        assertEquals(EnumStatusVenda.CANCELADA, vendaAtualizada.getStatus());

    }


    VendasModel vender() {


        LivrosModel livro = livrosRepository.save(criarLivro());
        int quantidadeInicial = livro.getQuantidade();

        ItemRequest item = new ItemRequest();
        item.setLivroId(livro.getId());
        item.setQuantidade(24);

        VendaRequest venda = new VendaRequest();
        venda.setFormaPagamento(EnumPagamentoVenda.PIX);
        venda.setItens(List.of(item));
        venda.setNumeroParcelas(0);


        VendasModel resultado = vendasService.vender(venda);


        assertNotNull(resultado);
        assertEquals(EnumPagamentoVenda.PIX, resultado.getFormaPagamento());
        assertFalse(resultado.getItens().isEmpty());


        BigDecimal esperado = livro.getPreco()
                .multiply(BigDecimal.valueOf(item.getQuantidade()));

        assertEquals(0, esperado.compareTo(resultado.getValorTotal()));


        LivrosModel livroAtualizado = livrosRepository
                .findById(livro.getId())
                .orElseThrow();

        int esperadoEstoque = quantidadeInicial - item.getQuantidade();

        assertEquals(esperadoEstoque, livroAtualizado.getQuantidade());

        return resultado;
    }


    private LivrosModel criarLivro() {
        LivrosModel livro = new LivrosModel();
        livro.setTitulo("Livro Teste");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setAnoCriacao(2010);
        livro.setAutor("Fulano");
        livro.setCategoria(EnumLivro.ACAO);
        livro.setDescricao("Descrição");
        livro.setQuantidade(100);
        return livro;
    }

}