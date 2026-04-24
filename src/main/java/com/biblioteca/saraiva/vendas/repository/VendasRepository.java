package com.biblioteca.saraiva.vendas.repository;

import com.biblioteca.saraiva.vendas.model.VendasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendasRepository extends JpaRepository<VendasModel, Long> {

    @Query("SELECT COALESCE(SUM(v.valorTotal), 0) FROM VendasModel v")
    Double somarTotalVendas();

    List<VendasModel> findByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);



    @Query("SELECT MAX(v.dataVenda) FROM VendasModel v")
    LocalDateTime buscarUltimaVenda();

    @Query("SELECT SUM(iv.quantidade) FROM ItemVenda iv")
    Long somarQuantidadePorVenda();
}