package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoDatabaseOptions {
    @Autowired
    DaoMenuOpciones dao = new DaoMenuOpciones();

    @Autowired
    private DataBooksRepository repoDataBooks;
    @Autowired
    private DataAuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBooks;

    //Buscar serie y guardarla en la base de datos
    public void lookingForBookToSave(String nombre){
        dao.lookingForBooksByName(nombre);

        DataBooks recordBook = dao.searchedBooks.get(0);
        DataBooksModel book = new DataBooksModel(recordBook);

        repoDataBooks.save(book);
    }
    //Buscar episodios y guardarlo en la base de datos

    //Mostrar todas las series buscadas

    //Buscar series por título

    //Top 5 mejores series

    //Buscar series por categorías

    //Filtrar series por el número de temporadas y su evaluación
    
    //Buscar episodios por nombre

    //Top 5 episodios por serie

}
