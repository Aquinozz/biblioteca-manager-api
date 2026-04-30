package com.biblioteca.saraiva.vendas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum  EnumVenda {


    CANCELADA ("C", "CANCELADA"),
    REALIZADA ("R", "REALIZADA");

    private  String codigo;
    private  String descricao;

    @JsonCreator
    public static EnumVenda situacaoVenda(String codigo){
        if (codigo == null) {
            throw new IllegalArgumentException("Código da venda não pode ser null");
        }

        for (EnumVenda status : values()) {
            if (status.codigo.equalsIgnoreCase(codigo)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Código inválido para EnumVenda: " + codigo);
        }
    }

