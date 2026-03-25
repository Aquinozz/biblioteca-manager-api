package com.biblioteca.saraiva.vendas.model;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private LivrosModel livro;


    //esse json ignore deixa o retorno mais limpo
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private VendasModel venda;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    // getters e setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VendasModel getVenda() {
        return venda;
    }

    public void setVenda(VendasModel venda) {
        this.venda = venda;
    }

    public LivrosModel getLivro() {
        return livro;
    }

    public void setLivro(LivrosModel livro) {
        this.livro = livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
