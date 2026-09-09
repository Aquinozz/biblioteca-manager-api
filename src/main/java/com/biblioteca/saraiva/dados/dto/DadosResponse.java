package com.biblioteca.saraiva.dados.dto;

import com.biblioteca.saraiva.vendas.utils.DataUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DadosResponse {

    private Double faturamentoTotal;
    private Long totalVendas;
    private Double ticketMedio;
    private Long totalLivros;

    @JsonFormat(pattern = DataUtils.DATA_TIME_PATTERN)
    private LocalDateTime ultimoRegistro;




}
