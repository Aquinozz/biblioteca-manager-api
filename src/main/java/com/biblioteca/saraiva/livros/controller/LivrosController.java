package com.biblioteca.saraiva.livros.controller;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.service.LivrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Livros", description = "Operações relacionadas aos livros")
@RestController
@RequestMapping("/livros")
public class LivrosController {

    private final LivrosService livrosService;

    public LivrosController(LivrosService livrosService) {
        this.livrosService = livrosService;
    }
1


    @Operation(summary = "Lista todos os livros cadastrados ou por categoria, autor e titulo")
    @GetMapping
    public ResponseEntity <?> listarLivros(
            @RequestParam (required = false) String autor,
            @RequestParam (required = false) String titulo,
            @RequestParam (required = false) String categoria,
            @RequestParam (required = false) Long id
            )
    {

        if (id != null){
            LivrosModel livroId = livrosService.buscarPorId(id);
            return ResponseEntity.ok(livroId);
         }


        List<LivrosModel> livros = livrosService.filtrar(autor, titulo, categoria);

        return ResponseEntity.ok(livros);
    }

    @Operation(summary = "Cria um novo livro")
    @PostMapping
    public LivrosModel criarLivro(@RequestBody LivrosModel livro){
        return livrosService.salvar(livro);
    }

    @Operation(summary = "Deleta um livro pelo ID")
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id){
        livrosService.deletar(id);
    }

    @Operation(summary = "Atualiza um livro existente")
    @PutMapping("/{id}")
    public LivrosModel atualizar(@PathVariable Long id, @RequestBody LivrosModel livro) {
        return livrosService.atualizar(id, livro);
    }
}