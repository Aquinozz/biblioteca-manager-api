package com.biblioteca.saraiva.dados.service;

import com.biblioteca.saraiva.dados.dto.DadosResponse;
import com.biblioteca.saraiva.dados.repository.DadosRepository;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DadosService {

    private VendasRepository vendasRepository;

    public DadosService(VendasRepository vendasRepository) {
        this.vendasRepository = vendasRepository;
    }


    public Long getTotalVendas() {
        return vendasRepository.count();

    }

    public DadosResponse getDadosGerais() {

        LocalDateTime ultimoRegistro = getRegistro();
        Double total = getFaturamentoTotal();
        Long quantidade = getTotalVendas();

        Double ticket = (quantidade == 0) ? 0.0 : total / quantidade;

        return new DadosResponse(total, quantidade, ticket, ultimoRegistro);
    }

    public LocalDateTime getRegistro() {
        return vendasRepository.buscarUltimaVenda();

    }

    public Double getFaturamentoTotal() {

        Double total = vendasRepository.somarTotalVendas();
        return total != null ? total : 0.0;

    }

    public String gerarHtmlSimples(DadosResponse dados) {
        return "<html><body>" +
                "<h1>Relatorio de Vendas</h1>" +
                "<p>Faturamento: R$ " + String.format("%.2f", dados.getFaturamentoTotal()) + "</p>" +
                "<p>Total de Vendas: " + dados.getTotalVendas() + "</p>" +
                "<p>Data: " + dados.getUltimoRegistro().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</p>" +
                "<p>Ticket Médio: R$ " + String.format("%.2f", dados.getTicketMedio()) + "</p>" +
                "</body></html>";
    }
}
