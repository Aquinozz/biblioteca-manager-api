package com.biblioteca.saraiva.vendas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemRequest {

    private Long livroId;
    private Integer quantidade;
}