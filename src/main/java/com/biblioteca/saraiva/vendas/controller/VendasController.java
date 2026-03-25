package com.biblioteca.saraiva.vendas.controller;

import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vendas", description = "Operações relacionadas às vendas de livros")
@RestController
@RequestMapping("/vendas")
public class VendasController {

    private final VendasService vendasService;

    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @Operation(summary = "Lista todas as vendas realizadas")
    @GetMapping
    public List<VendasModel> listarVendas(){
        return vendasService.listarVendas();
    }

    @Operation(summary = "Realiza uma nova venda de livros")
    @PostMapping
    public VendasModel vender(@RequestBody VendaRequest request) {
        return vendasService.vender(request);
    }
}