package com.biblioteca.saraiva.vendas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum EnumPagamentoVenda {

    PIX ("PIX"),
    DEBITO ("DEBITO"),
    CREDITO ("CREDITO");

    private String formaPagamento;
}
