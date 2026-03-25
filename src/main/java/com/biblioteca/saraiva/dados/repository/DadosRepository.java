package com.biblioteca.saraiva.dados.repository;

import com.biblioteca.saraiva.dados.model.DadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DadosRepository extends JpaRepository<DadosModel, Long> {

    List<DadosModel> findAllByOrderByDataGeracaoDesc();
    boolean existsByDataGeracaoBetween(LocalDateTime inicio, LocalDateTime fim);
}