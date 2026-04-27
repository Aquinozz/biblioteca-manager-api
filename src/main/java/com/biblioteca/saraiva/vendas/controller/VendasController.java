package com.biblioteca.saraiva.vendas.controller;



import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.repository.VendasRepository;
import com.biblioteca.saraiva.vendas.service.VendasService;
import com.biblioteca.saraiva.vendas.utils.DataUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Vendas", description = "Operações relacionadas às vendas de livros")
@RestController
@RequestMapping("/vendas")
public class VendasController {


    @Autowired
    private VendasRepository vendasRepository;

    private final VendasService vendasService;

    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @Operation(summary = "Lista todas as vendas realizadas")
    @GetMapping(path = {"", "/{id}"})
    public ResponseEntity <?>  listarVendas(
            @Parameter(
                    description = "Data inicial (dd/MM/yyyy)",
                    example = "01/01/2026",
                    schema = @Schema(type = "string")
            )
            @RequestParam (required = false) @DateTimeFormat(pattern = DataUtils.DATA_PATTERN)LocalDate inicio,

            @Parameter(
                    description = "Data final (dd/MM/yyyy)",
                    example = "02/02/2026",
                    schema = @Schema(type = "string")
            )
            @RequestParam (required = false) @DateTimeFormat (pattern = DataUtils.DATA_PATTERN) LocalDate fim,

            @Parameter(description = "ID da venda")
            @RequestParam(required = false) Long id

    ){



        if (id != null){
            VendasModel vendaUnica = vendasService.buscarPorId(id);
            return ResponseEntity.ok(vendaUnica);
        }

        if (inicio != null && fim != null){
            List<VendasModel> vendas = vendasRepository.findByDataVendaBetween(inicio, fim);
            return ResponseEntity.ok (vendas);
        }





        return ResponseEntity.ok(vendasRepository.findAll());
    }


    @Operation(summary = "Acha venda pelo Id")
    @GetMapping("/{id}")
    public ResponseEntity<VendasModel> buscarVenda(@PathVariable Long id) {
        VendasModel venda = vendasService.buscarPorId(id);
        return ResponseEntity.ok(venda);
    }

    @Operation(summary = "Cancela venda pelo id")
    @DeleteMapping ("/{id}")
    public void deletarVenda (@PathVariable Long id){
        vendasService.cancelarVenda(id);
    }


    @Operation(summary = "Realiza uma nova venda de livros")
    @PostMapping
    public VendasModel vender(@RequestBody VendaRequest request) {
        return vendasService.vender(request);
    }
}