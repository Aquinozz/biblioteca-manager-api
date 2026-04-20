package com.biblioteca.saraiva.dados.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DadosResponse {

    private Double faturamentoTotal;
    private Long totalVendas;
    private Double ticketMedio;

    @JsonFormat (pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime ultimoRegistro;

    public DadosResponse(Double faturamentoTotal, Long totalVendas, Double ticketMedio, LocalDateTime ultimoRegistro) {
        this.faturamentoTotal = faturamentoTotal;
        this.totalVendas = totalVendas;
        this.ticketMedio = ticketMedio;
        this.ultimoRegistro = ultimoRegistro;
    }

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
