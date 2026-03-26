package com.biblioteca.saraiva.livros.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Livros")

@Schema(description = "Modelo de Livro")
public class LivrosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String titulo;
    private String autor;
    private String descricao;
    private String categoria;
    private Integer anoCriacao;
    private BigDecimal preco;
    private Integer quantidade;


    public String getCategoria() {return categoria;}

    public void setCategoria(String categoria){this.categoria = categoria;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAnoCriacao() {
        return anoCriacao;
    }

    public void setAnoCriacao(Integer anoCriacao) {
        this.anoCriacao = anoCriacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
