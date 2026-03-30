package com.biblioteca.saraiva.vendas.repository;

import com.biblioteca.saraiva.vendas.model.VendasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendasRepository extends JpaRepository<VendasModel, Long> {

    @Query("SELECT COALESCE(SUM(v.valorTotal), 0) FROM VendasModel v")
    Double somarTotalVendas();
}