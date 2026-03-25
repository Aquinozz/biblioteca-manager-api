package com.biblioteca.saraiva.dados.controller;

import com.biblioteca.saraiva.dados.model.DadosModel;
import com.biblioteca.saraiva.dados.service.DadosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dados", description = "Métricas e estatísticas do sistema de vendas")
@RestController
@RequestMapping("/dados")
public class DadosController {

    private final DadosService dadosService;

    public DadosController(DadosService dadosService){
        this.dadosService = dadosService;
    }

    @Operation(summary = "Retorna o faturamento total das vendas")
    @GetMapping("/faturamento")
    public Double getFaturamento(){
        return dadosService.getFaturamentoTotal();
    }

    @Operation(summary = "Retorna o total de vendas realizadas")
    @GetMapping("/total-vendas")
    public Long getTotalVendas(){
        return dadosService.getTotalVendas();
    }

    @Operation(summary = "Retorna o ticket médio das vendas")
    @GetMapping("/ticket-medio")
    public Double getTicketMedio(){
        return dadosService.getTicketMedio();
    }

    @Operation(summary = "Salva um snapshot do histórico de dados")
    @PostMapping("/historico")
    public DadosModel salvarHistorico(){
        return dadosService.salvarHistorico();
    }

    @Operation(summary = "Lista o histórico de métricas salvas")
    @GetMapping("/historico")
    public List<DadosModel> listarHistorico(){
        return dadosService.getHistorico();
    }
}