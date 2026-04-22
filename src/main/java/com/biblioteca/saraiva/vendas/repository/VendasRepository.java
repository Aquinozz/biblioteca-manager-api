package com.biblioteca.saraiva.vendas.repository;

import com.biblioteca.saraiva.vendas.model.VendasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface VendasRepository extends JpaRepository<VendasModel, Long> {

    @Query("SELECT COALESCE(SUM(v.valorTotal), 0) FROM VendasModel v")
    Double somarTotalVendas();


    @Query("SELECT MAX(v.dataVenda) FROM VendasModel v")
    LocalDateTime buscarUltimaVenda();


}