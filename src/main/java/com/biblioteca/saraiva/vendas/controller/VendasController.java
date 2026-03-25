package com.biblioteca.saraiva.vendas.controller;

<<<<<<< HEAD
import com.biblioteca.saraiva.livros.model.LivrosModel;
=======
>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3
import com.biblioteca.saraiva.vendas.dto.VendaRequest;
import com.biblioteca.saraiva.vendas.model.VendasModel;
import com.biblioteca.saraiva.vendas.service.VendasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3
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

<<<<<<< HEAD
    @Operation(summary = "Acha venda pelo Id")
    @GetMapping("/{id}")
    public ResponseEntity<VendasModel> buscarVenda(@PathVariable Long id) {
        VendasModel venda = vendasService.buscarPorId(id);
        return ResponseEntity.ok(venda);
    }

=======
>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3
    @Operation(summary = "Realiza uma nova venda de livros")
    @PostMapping
    public VendasModel vender(@RequestBody VendaRequest request) {
        return vendasService.vender(request);
    }
}