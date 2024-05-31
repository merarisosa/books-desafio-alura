package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.modelos.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BooksModel, Long> {
}
