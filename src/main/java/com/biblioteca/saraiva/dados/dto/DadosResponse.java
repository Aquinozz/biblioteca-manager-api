package com.biblioteca.saraiva.dados.dto;

public class DadosResponse {

    private Double faturamentoTotal;
    private Long totalVendas;
    private Double ticketMedio;

    public DadosResponse(Double faturamentoTotal, Long totalVendas, Double ticketMedio) {
        this.faturamentoTotal = faturamentoTotal;
        this.totalVendas = totalVendas;
        this.ticketMedio = ticketMedio;
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

}
