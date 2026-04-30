package com.biblioteca.saraiva.vendas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

@Schema(description = "Dados para realizar uma venda")
public class VendaRequest {

    @Schema(example = "PIX")
    private String formaPagamento;

    private List<ItemRequest> itens;


}