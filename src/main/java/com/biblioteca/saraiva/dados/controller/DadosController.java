package com.biblioteca.saraiva.dados.controller;

import com.biblioteca.saraiva.dados.dto.DadosResponse;
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


    @Operation(summary = "Retorna um resumo de dados sobre lucro e etc...")
    @GetMapping
    public DadosResponse getDados(){
        return dadosService.getDadosGerais();
    }

}