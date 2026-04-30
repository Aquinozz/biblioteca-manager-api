package com.biblioteca.saraiva.dados.dto;

import com.biblioteca.saraiva.vendas.utils.DataUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DadosResponse {

    private Double faturamentoTotal;
    private Long totalVendas;
    private Double ticketMedio;
    private Long totalLivros;

    @JsonFormat(pattern = DataUtils.DATA_TIME_PATTERN)
    private LocalDateTime ultimoRegistro;


    public DadosResponse(Double faturamentoTotal, Long totalVendas, Double ticketMedio, LocalDateTime ultimoRegistro, Long totalLivros) {
        this.faturamentoTotal = faturamentoTotal;
        this.totalVendas = totalVendas;
        this.ticketMedio = ticketMedio;
        this.ultimoRegistro = ultimoRegistro;
        this.totalLivros = totalLivros;
    }



    public Long getLivrosVendidos() {return totalLivros;}

    public Double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public Double getTicketMedio() {
        return ticketMedio;
    }

    public LocalDateTime getUltimoRegistro() {return ultimoRegistro;}

}
