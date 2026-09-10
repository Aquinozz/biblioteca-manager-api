package com.biblioteca.saraiva.livros.service;

import com.biblioteca.saraiva.livros.dto.LivroRequest;
import com.biblioteca.saraiva.livros.enums.EnumLivro;
import com.biblioteca.saraiva.livros.model.LivrosModel;
import com.biblioteca.saraiva.livros.repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LivrosService {

    private final LivrosRepository livrosRepository;

    public LivrosModel buscarPorId(Long id) {
        log.info("Buscando livro por ID: {}", id);

        return livrosRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Livro não encontrado - ID: {}", id);
                    return new RuntimeException("Livro não encontrado");
                });
    }

    public LivrosModel salvar(LivroRequest req) {

        log.info("Pedindo request....");

        LivrosModel livro = new LivrosModel();

        log.info("Instanciando livro");

        livro.setQuantidade(req.getQuantidade());
        livro.setAnoCriacao(req.getAnoCriacao());
        livro.setAutor(req.getAutor());
        livro.setDescricao(req.getDescricao());
        livro.setPreco(req.getPreco());
        livro.setCategoria(req.getCategoria());
        livro.setTitulo(req.getTitulo());


        log.info("Salvando livro - titulo: {}, autor: {}", livro.getTitulo(), livro.getAutor());

        log.info("Livro criado com sucesso");
        return livrosRepository.save(livro);
    }

    public void deletar(Long id) {
        log.info("Tentando deletar livro - ID: {}", id);

        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Livro não encontrado para deletar - ID: {}", id);
                    return new RuntimeException("Livro não encontrado");
                });

        log.info("Livro deletado com sucesso - ID: {}", id);
        livrosRepository.delete(livro);
    }

    public List<LivrosModel> filtrar (String autor, String titulo, EnumLivro categoria){

        log.info("Filtrando livros - autor: {}, titulo: {}, categoria: {}",
                autor, titulo, categoria);

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

    public LivrosModel atualizar(Long id, LivroRequest req){


        log.info("Atualizando livro - ID: {}", id);

        LivrosModel livro = livrosRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Livro não encontrado para atualizar - ID: {}", id);
                    return new RuntimeException("Livro não encontrado");
                });

        if (req.getTitulo() != null) {
            livro.setTitulo(req.getTitulo());
        }
        if (req.getAutor() != null) {
            livro.setAutor(req.getAutor());
        }
        if (req.getDescricao() != null) {
            livro.setDescricao(req.getDescricao());
        }
        if (req.getAnoCriacao() != null) {
            livro.setAnoCriacao(req.getAnoCriacao());
        }
        if (req.getPreco() != null) {
            livro.setPreco(req.getPreco());
        }
        if (req.getQuantidade() != null) {
            livro.setQuantidade(req.getQuantidade());
        }

        log.info("Livro atualizado com sucesso - ID: {}", id);
        return livrosRepository.save(livro);
    }
}