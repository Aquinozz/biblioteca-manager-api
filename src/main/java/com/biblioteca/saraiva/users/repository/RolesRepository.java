package com.biblioteca.saraiva.users.repository;

import com.biblioteca.saraiva.users.models.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByNome(String nome);

    boolean existsByNome(String nome);
}