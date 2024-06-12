package com.example.desafiobooks.repository;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataBooksRepository extends JpaRepository<DataBooksModel, Long> {
    Optional<DataBooksModel> findByTitulo(String titulo);

    //encontrar el top 5 de mejores libros
    List<DataBooksModel> findTop5ByOrderByNumeroDescargasDesc();

    //encontrar segun categorias
    List<DataBooksModel> findByBookshelves(DataBookshelves dataBookshelves);

    //encontrar libros por nombre
    @Query("SELECT l FROM DataBooksModel l WHERE l.titulo ILIKE %:nombreLibro%")
    List<DataBooksModel> librosPorNombre(String nombreLibro);

 //   @Query("SELECT DISTINCT i.nombre FROM Idioma i")
   // List<String> findDistinctIdiomas();

    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM idiomas")
    List<String> findDistinctIdiomas();

    List<DataBooksModel> findByIdiomas(String idioma);

}
