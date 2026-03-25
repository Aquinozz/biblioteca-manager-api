package com.biblioteca.saraiva.livros.controller;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.service.LivrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3
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

<<<<<<< HEAD
    @Operation(summary = "Acha livro pelo Id")
    @GetMapping("/{id}")
    public ResponseEntity<LivrosModel> buscarLivro(@PathVariable Long id) {
        LivrosModel livro = livrosService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

=======
>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3
    @Operation(summary = "Lista todos os livros cadastrados")
    @GetMapping
    public List<LivrosModel> listarLivros(){
        return livrosService.listarTodos();
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