package com.biblioteca.saraiva.livros.service;


import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrosService {

    // Realiza uma conexão "Automatica" com o DB
    @Autowired
    private LivrosRepository livrosRepository;


<<<<<<< HEAD
    public LivrosModel buscarPorId(Long id) {
        return livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
=======

>>>>>>> 6b965b9a60a57c94dbb9c4442fc08bed6fc7f9a3

    public List<LivrosModel> listarTodos() {
        return livrosRepository.findAll();
    }


    public LivrosModel salvar(LivrosModel livro) {
        return livrosRepository.save(livro);
    }



    public void deletar(Long id) {
        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livrosRepository.delete(livro);
    }


    public LivrosModel atualizar(Long id, LivrosModel dadosAtualizados){

        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(dadosAtualizados.getTitulo());
        livro.setAutor(dadosAtualizados.getAutor());
        livro.setDescricao(dadosAtualizados.getDescricao());
        livro.setAnoCriacao(dadosAtualizados.getAnoCriacao());
        livro.setPreco(dadosAtualizados.getPreco());
        livro.setQuantidade(dadosAtualizados.getQuantidade());

        return livrosRepository.save(livro);
    }
}
