package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataBooksRepository extends JpaRepository<DataBooksModel, Long> {
    Optional<DataBooksModel> findByTitulo(String titulo);

    //encontrar el top 5 de mejores libros
    List<DataBooksModel> findTop5ByOrderByNumeroDescargasDesc();

    //encontrar segun categorias
    List<DataBooksModel> findByBookshelves(DataBookshelves dataBookshelves);
}
