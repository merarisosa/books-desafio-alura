package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBooksRepository extends JpaRepository<DataBooksModel, Long> {
}
