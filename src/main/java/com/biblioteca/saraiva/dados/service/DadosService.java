package com.biblioteca.saraiva.dados.service;

import com.biblioteca.saraiva.dados.dto.DadosResponse;
import com.biblioteca.saraiva.dados.repository.DadosRepository;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DadosService {

    private VendasRepository vendasRepository;
    private DadosRepository dadosRepository;

    public DadosService(VendasRepository vendasRepository, DadosRepository dadosRepository){
        this.vendasRepository = vendasRepository;
        this.dadosRepository = dadosRepository;
    }


    public Long getTotalVendas(){
        return vendasRepository.count();

    }

    public DadosResponse getDadosGerais(){

        LocalDateTime ultimoRegistro = getRegistro();
        Double total = getFaturamentoTotal();
        Long quantidade = getTotalVendas();

        Double ticket = (quantidade == 0) ? 0.0 : total / quantidade;

        return new DadosResponse(total, quantidade, ticket, ultimoRegistro);
    }

    public LocalDateTime getRegistro(){
        return vendasRepository.buscarUltimaVenda();

    }

    public Double getFaturamentoTotal() {

        Double total = vendasRepository.somarTotalVendas();
        return total != null ? total : 0.0;

    }

}
