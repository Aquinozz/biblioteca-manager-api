package com.biblioteca.saraiva.vendas.service;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import com.biblioteca.saraiva.vendas.dto.ItemRequest;
import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class VendasServiceTest {


    @Autowired
    VendasService vendasService;

    @Autowired
    LivrosRepository livrosRepository;

    @Test
    void vender() {

        LivrosModel livro = livrosRepository.save(criarLivro());

        ItemRequest item = new ItemRequest();

        item.setLivroId(livro.getId());
        item.setQuantidade(24);

        VendaRequest venda = new VendaRequest();

        venda.setFormaPagamento("PIX");
        venda.setItens(List.of(item));

        VendasModel resultado = vendasService.vender(venda);



        if (livro.getQuantidade() < item.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente para o livro: " + livro.getTitulo());
        }

        assertNotNull(resultado);
        assertEquals("PIX", resultado.getFormaPagamento());
        assertFalse(resultado.getItens().isEmpty());
        assertEquals(new BigDecimal("1200.00"), resultado.getValorTotal());



    }


    private LivrosModel criarLivro() {
        LivrosModel livro = new LivrosModel();
        livro.setTitulo("Livro Teste");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setAnoCriacao(2010);
        livro.setAutor("Fulano");
        livro.setCategoria("aventura");
        livro.setDescricao("Descrição");
        livro.setQuantidade(100);
        return livro;
    }
}