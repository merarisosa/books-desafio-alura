package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class DaoMenuOpciones {
    List<DataBooks> searchedBooks = new ArrayList<>();
    List<DataBooksModel> searchedBooksModel = new ArrayList<>();

    @Autowired
    DaoConsumoAPI dao = new DaoConsumoAPI();

    @Autowired
    private DataBooksRepository repoDataBooks;
    @Autowired
    private DataAuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBooks;

    //Top 10 libros más descargados
    public void showTopBooks(){
        AtomicInteger indexTopBooks = new AtomicInteger(1);

        System.out.println("Top 10 libros más descargados");
        dao.showDataFromAPI().informacionLibros().stream()
                .sorted(Comparator.comparing(DataBooks::numeroDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(
                        book -> {
                            System.out.println(indexTopBooks.getAndIncrement() + ". " + book);
                        });
    }

    //Busqueda de libros por nombre como record
    public void lookingForBooksByName(String nombreBusqueda){
        Optional<DataBooks> searchedBook = dao.showDataFromAPI().informacionLibros().stream()
                .filter(nombre -> nombre.titulo().toUpperCase().contains(nombreBusqueda.toUpperCase()))
                .findFirst();

        if(searchedBook.isPresent()){
            System.out.println("Libro encontrado en The Books!");
            System.out.println(searchedBook.get());
            searchedBooks.add(searchedBook.get());


        } else{
            System.out.println("El libro ingresado no se encuentra en The Books :(");
        }
    }

    //Trabajando con estadisticas
    public void gettingStatistics(){
        DoubleSummaryStatistics dse = dao.showDataFromAPI().informacionLibros().stream()
                .filter(descargas -> descargas.numeroDescargas() > 0)
                .collect(Collectors.summarizingDouble(DataBooks::numeroDescargas));

        System.out.println("La cantidad media de descargas en The Books! es : "+ dse.getAverage());
        System.out.println("La cantidad máxima de descargas en The Books! es : "+ dse.getMax());
        System.out.println("La cantidad mínima de descargas en The Books! es : "+ dse.getMin());
        System.out.println("La cantidad de registros evaluados en The Books! es: "+ dse.getCount());
    }

    // Obtener las ultimas consultas de busqueda como record
    public void latestSearches(){
        AtomicInteger indexLatestSearches = new AtomicInteger(1);

        System.out.println("Últimas consultas de búsqueda como record:");
        if(!searchedBooks.isEmpty()){
            searchedBooks.forEach(book -> {
                System.out.println(indexLatestSearches.getAndIncrement() + ". "+ book);
            });
        } else {
            System.out.println("No hay búsquedas recientes, consulta algo nuevo :).");
        }
    }

    // Buscar libros según la categoría elegida
    public void lookingForBooksByCategories(String categoria){
        AtomicInteger indexBooksByCategories = new AtomicInteger(1);
        List<DataBooks> filteredBooksByCategorie = new ArrayList<>();
        List<DataBooks> booksFromAPI = dao.showDataFromAPI().informacionLibros();
        DataBookshelves categorieToEnum;

        try {
            categorieToEnum = DataBookshelves.fromSpanish(categoria);
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría no válida: " + categoria);
            return;
        }

        if (booksFromAPI == null) {
            System.out.println("No se pudieron obtener los libros de la API.");
            return;
        }

        for (DataBooks book : booksFromAPI) {
            if (book.bookshelves().contains(categorieToEnum.getBookshelves())) {
                filteredBooksByCategorie.add(book);
            }
        }

        if (!filteredBooksByCategorie.isEmpty()) {
            System.out.println("Libros de la categoría " + categoria.toUpperCase() + " hallados en The Books!");
            for (DataBooks dataBooks : filteredBooksByCategorie) {
                System.out.println(indexBooksByCategories.getAndIncrement() + ". " + dataBooks.titulo());
            }
        } else {
            System.out.println("No existen libros disponibles para la categoría " + categoria.toUpperCase());
        }
    }

    //Muestra las categorias disponibles en el enum
    public void showAllCategories() {
        AtomicInteger indexCategories = new AtomicInteger(1);

        System.out.println("Categorias disponibles en The Books!");
        DataBookshelves[] categorias = DataBookshelves.values();

        for (DataBookshelves categoria : categorias) {
            System.out.println(indexCategories.getAndIncrement() + ". " + categoria.getCategorieSpanish());
        }
    }
}
