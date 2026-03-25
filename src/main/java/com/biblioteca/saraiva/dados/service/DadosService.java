package com.biblioteca.saraiva.dados.service;

import org.springframework.scheduling.annotation.Scheduled;
import com.biblioteca.saraiva.dados.model.DadosModel;
import com.biblioteca.saraiva.dados.repository.DadosRepository;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DadosService {

    private VendasRepository vendasRepository;
    private DadosRepository dadosRepository;

    public DadosService(VendasRepository vendasRepository, DadosRepository dadosRepository){
        this.vendasRepository = vendasRepository;
        this.dadosRepository = dadosRepository;
    }

    public Double getFaturamentoTotal(){

        Double total = vendasRepository.somarTotalVendas();
        return total != null ? total : 0.0;
    }

    public Long getTotalVendas(){
        return vendasRepository.count();

    }

    public Double getTicketMedio(){

        if (dadosRepository.existsByDataGeracaoBetween(
                LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().atTime(23,59,59)
        )) {
            return null;
        }
            {

                Double total = getFaturamentoTotal();
                Long quantidade = getTotalVendas();

                if (quantidade == 0) return 0.0;

                return total / quantidade;
            }
        }

    public DadosModel salvarHistorico(){
        Double faturamento = getFaturamentoTotal();
        Long total = getTotalVendas();
        Double ticket = getTicketMedio();

        DadosModel dados = new DadosModel();
        dados.setFaturamento(faturamento);
        dados.setTotalVendas(total);
        dados.setTicketMedio(ticket);
        dados.setDataGeracao(LocalDateTime.now());

        return dadosRepository.save(dados);
    }

    public List<DadosModel> getHistorico() {
        return dadosRepository.findAllByOrderByDataGeracaoDesc();
    }

    @Scheduled(cron = "0 0 0 * * ?") // todo dia à meia-noite
    public void salvarHistoricoAutomatico() {
        salvarHistorico();
    }


}
