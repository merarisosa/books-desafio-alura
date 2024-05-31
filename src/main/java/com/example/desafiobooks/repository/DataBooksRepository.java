package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataBooksRepository extends JpaRepository<DataBooksModel, Long> {
    Optional<DataBooksModel> findByTitulo(String titulo);
}
