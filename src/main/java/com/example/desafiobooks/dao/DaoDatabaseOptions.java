package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DaoDatabaseOptions {
    @Autowired
    DaoMenuOpciones dao = new DaoMenuOpciones();
    @Autowired
    DaoConsumoAPI daoAPI = new DaoConsumoAPI();
    @Autowired
    private DataBooksRepository repoDataBooks;
    @Autowired
    private DataAuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBooks;

    //Guarda all books en la bd
    public void saveAllBooksFromAPI(){
        daoAPI.showBooksAsClass();
        var size = daoAPI.dataBooksModelList.size();
        for (int i = 0; i < size; i++) {
            DataBooksModel dataBooksModel = daoAPI.dataBooksModelList.get(i);
            if (repoDataBooks.findByTitulo(dataBooksModel.getTitulo()).isPresent()) {
                System.out.println("Libro ya registrado en la base de datos: " + dataBooksModel.getTitulo());
            } else {
                repoDataBooks.save(dataBooksModel);
            }
        }
    }

    //Buscar libro y guardarla en la base de datos
    public void lookingForBookToSave(String nombre){
        dao.lookingForBooksByName(nombre);
        DataBooks recordBook = dao.searchedBooks.get(0);
        DataBooksModel book = new DataBooksModel(recordBook);
        if(repoDataBooks.findByTitulo(recordBook.titulo()).isPresent()){
            System.out.println("El libro "+ book.getTitulo()+ " ya ha sido añadido a la base de datos");
        }else{
            repoDataBooks.save(book);
        }
    }

    //Buscar las ultimas inserciones de busqueda en la bd
    public void latestSearchesAsDatabase(){
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataBooksModel> allBooks = repoDataBooks.findAll();

        System.out.println("Últimas consultas en la base de datos:");
        if(!allBooks.isEmpty()){
            allBooks.forEach(book -> {
                System.out.println(indexLatestSearches.getAndIncrement() + ". "+ book);
            });
        } else {
            System.out.println("No hay búsquedas recientes en la base de datos, consulta algo nuevo :).");
        }
    }

    //Mostrar todas las series buscadas

    //Buscar series por título

    //Top 5 mejores series

    //Buscar series por categorías

    //Filtrar series por el número de temporadas y su evaluación
    
    //Buscar episodios por nombre

    //Top 5 episodios por serie

}
