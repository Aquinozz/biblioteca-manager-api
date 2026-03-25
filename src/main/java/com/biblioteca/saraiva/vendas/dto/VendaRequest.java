package com.biblioteca.saraiva.vendas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Dados para realizar uma venda")
public class VendaRequest {

    @Schema(example = "PIX")
    private String formaPagamento;

    private List<ItemRequest> itens;

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemRequest> itens) {
        this.itens = itens;
    }
}