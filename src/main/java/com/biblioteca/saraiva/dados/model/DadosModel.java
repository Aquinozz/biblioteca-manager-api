package com.biblioteca.saraiva.dados.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class DadosModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Double faturamento;
    private  Long totalVendas;
    private  Double ticketMedio;
    private LocalDateTime dataGeracao;

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(Double faturamento) {
        this.faturamento = faturamento;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public Double getTicketMedio() {
        return ticketMedio;
    }

    public void setTicketMedio(Double ticketMedio) {
        this.ticketMedio = ticketMedio;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
