package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.modelos.DataAuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataAuthorRepository extends JpaRepository<DataAuthorModel, Long> {
    Optional<DataAuthorModel> findByNombre(String nombre);
}
