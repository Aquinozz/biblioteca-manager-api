package com.biblioteca.saraiva.livros.repository;

import com.biblioteca.saraiva.livros.enums.EnumLivro;
import com.biblioteca.saraiva.livros.model.LivrosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivrosRepository extends JpaRepository<LivrosModel, Long> {
    List<LivrosModel> findByAutorContainingIgnoreCase(String autor);
    List<LivrosModel> findByTituloContainingIgnoreCase(String titulo);


    List<LivrosModel> findByCategoria (EnumLivro categoria);

}