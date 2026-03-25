package com.biblioteca.saraiva.livros.repository;

import com.biblioteca.saraiva.livros.model.LivrosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<LivrosModel, Long> {

}