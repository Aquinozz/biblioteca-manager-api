package com.biblioteca.saraiva.livros.service;


import com.biblioteca.saraiva.livros.enums.EnumLivro;
import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LivrosService {

    // Realiza uma conexão "Automatica" com o DB
    @Autowired
    private LivrosRepository livrosRepository;



    public LivrosModel buscarPorId(Long id) {
        return livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }




    public LivrosModel salvar(LivrosModel livro) {


        log.info("Livro criado com sucesso");
        return livrosRepository.save(livro);
    }



    public void deletar(Long id) {
        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        log.info("Livro deletado com sucesso");
        livrosRepository.delete(livro);
    }


    public List<LivrosModel> filtrar (String autor, String titulo, EnumLivro categoria){



        if (autor != null) {
            return livrosRepository.findByAutorContainingIgnoreCase(autor);
        }

        if (titulo != null){
            return livrosRepository.findByTituloContainingIgnoreCase(titulo);
        }

        if (categoria != null){
           return livrosRepository.findByCategoria(categoria);
        }

        return livrosRepository.findAll();
    }

    


    public LivrosModel atualizar(Long id, LivrosModel dadosAtualizados){
        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (dadosAtualizados.getTitulo() != null) {
            livro.setTitulo(dadosAtualizados.getTitulo());
        }
        if (dadosAtualizados.getAutor() != null) {
            livro.setAutor(dadosAtualizados.getAutor());
        }
        if (dadosAtualizados.getDescricao() != null) {
            livro.setDescricao(dadosAtualizados.getDescricao());
        }
        if (dadosAtualizados.getAnoCriacao() != null) {
            livro.setAnoCriacao(dadosAtualizados.getAnoCriacao());
        }
        if (dadosAtualizados.getPreco() != null) {
            livro.setPreco(dadosAtualizados.getPreco());
        }
        if (dadosAtualizados.getQuantidade() != null) {
            livro.setQuantidade(dadosAtualizados.getQuantidade());
        }
        log.info("Livro atualizado com sucesso");
        return livrosRepository.save(livro);
    }
}
