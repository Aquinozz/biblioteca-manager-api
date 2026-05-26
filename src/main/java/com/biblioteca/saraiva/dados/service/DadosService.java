package com.biblioteca.saraiva.dados.service;

import com.biblioteca.saraiva.dados.dto.DadosResponse;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DadosService {

    private VendasRepository vendasRepository;

    public DadosService(VendasRepository vendasRepository) {
        this.vendasRepository = vendasRepository;
    }

    public Long getTotalVendas() {
        log.info("Realizando contagem de total de vendas...");

        Long total = vendasRepository.count();

        log.info("Total de vendas é igual a: {}", total);

        return total;
    }

    public DadosResponse getDadosGerais() {

        log.info("Pegando dados gerais");

        LocalDateTime ultimoRegistro = getRegistro();
        Double total = getFaturamentoTotal();
        Long quantidade = getTotalVendas();
        Long livrosVendidos = getLivrosVendidos();
        Double ticket = getTicketMedio();

        log.info("Dados gerais calculados - faturamento: {}, vendas: {}, ticketMedio: {}, livrosVendidos: {}",
                total, quantidade, ticket, livrosVendidos);

        log.info("Get dados gerais realizado com sucesso");

        return new DadosResponse(total, quantidade, ticket, ultimoRegistro, livrosVendidos);
    }

    public LocalDateTime getRegistro() {
        log.info("Última venda sendo buscada...");

        LocalDateTime ultimaVenda = vendasRepository.buscarUltimaVenda();

        log.info("Última venda foi: {}", ultimaVenda);

        return ultimaVenda;
    }

    public Double getTicketMedio(){
        log.info("Calculando ticket médio...");

        Double total = getFaturamentoTotal();
        Long quantidade = getTotalVendas();

        Double ticket = (quantidade == 0) ? 0.0 : total / quantidade;

        log.info("Ticket médio é igual a: {}", ticket);

        return ticket;
    }

    public Long getLivrosVendidos() {

        log.info("Calculando total de livros vendidos...");

        Long totalLivros = vendasRepository.somarQuantidadePorVenda();

        Long resultado = totalLivros != null ? totalLivros : 0;

        log.info("Total de livros vendidos: {}", resultado);

        return resultado;
    }

    public Double getFaturamentoTotal() {

        log.info("Calculando faturamento total...");

        Double total = vendasRepository.somarTotalVendas();
        Double faturamento = total != null ? total : 0.0;

        log.info("Faturamento total foi de: {}", faturamento);

        return faturamento;
    }

    public String gerarHtml(DadosResponse dados) {

        log.info("Gerando HTML de relatório...");

        String html = "<html><body>" +
                "<h1>Relatorio de Vendas</h1>" +
                "<p>Faturamento: R$ " + String.format("%.2f", dados.getFaturamentoTotal()) + "</p>" +
                "<p>Total de Vendas: " + dados.getTotalVendas() + "</p>" +
                "<p>Data: " + dados.getUltimoRegistro().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</p>" +
                "<p>Ticket Médio: R$ " + String.format("%.2f", dados.getTicketMedio()) + "</p>" +
                "<p> Total de livros vendidos: " + dados.getTotalVendas() + "</p>" +
                "</body></html>";

        log.info("Html de dados gerado com sucesso");

        return html;
    }
}