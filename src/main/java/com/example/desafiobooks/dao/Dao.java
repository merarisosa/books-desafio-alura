package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.records.Books;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.service.ConsumoAPI;
import com.example.desafiobooks.service.ConvierteDatosToClass;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Dao {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatosToClass convierteDatosToClass = new ConvierteDatosToClass();
    private String BASE_URL = "https://gutendex.com/books/";
    List<DataBooks> searchedBooks = new ArrayList<>();


    //Obtener los datos de la API para manipular en formato json y en formato class
    public Books showDataFromAPI(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, Books.class);

        return dataToClass;
    }

    //Ver la API como clase Books
    public void verAPI(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, Books.class);
        System.out.println("*****************************************************");
        System.out.println("Data converted to record Books: ");
        System.out.println(dataToClass);
        System.out.println("*****************************************************");
    }

    //Ver libros como clase DataBooks
    public void verLibrosAsClass(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, DataBooks.class);
        System.out.println("*****************************************************");
        System.out.println("Data converted to class DataBooks: ");
        System.out.println(dataToClass);
        System.out.println("*****************************************************");
    }

    //Top 10 libros más descargados
    public void showTopBooks(){
        AtomicInteger indexTopBooks = new AtomicInteger(1);

        System.out.println("Top 10 libros más descargados");
        showDataFromAPI().informacionLibros().stream()
                .sorted(Comparator.comparing(DataBooks::numeroDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(
                        book -> {
                        System.out.println(indexTopBooks.getAndIncrement() + ". " + book);
                        });
    }

    //Busqueda de libros por nombre
    public void lookingForBooksByName(String nombreBusqueda){
        var dataJson = consumoAPI.consumirAPI(BASE_URL+"?search="+nombreBusqueda.replace(" ", "+"));
        var dataBusqueda = convierteDatosToClass.obtenerDatos(dataJson, Books.class);

        Optional<DataBooks> searchedBook = dataBusqueda.informacionLibros().stream()
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
        DoubleSummaryStatistics dse = showDataFromAPI().informacionLibros().stream()
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

    // Obtener las ultimas consultas de busqueda como clase
    public void lastestSearchesAsClass(){
        AtomicInteger indexLatestSearchesAsClass = new AtomicInteger(1);

        verLibrosAsClass();
        //System.out.println("Últimas consultas de búsqueda como clase:");

    }

    //Buscar libros segun la categoria elegida
    public void lookingForBooksByCategories(String categoria){
        AtomicInteger indexBooksByCategories = new AtomicInteger(1);
        List<DataBooks> filteredBooksByCategorie = new ArrayList<>();

        for (DataBooks book : showDataFromAPI().informacionLibros()) {
            if (book.bookshelves().contains(categoria)) {
                filteredBooksByCategorie.add(book);
            }
        }

        if(!filteredBooksByCategorie.isEmpty()){
            System.out.println("Libros de la categoria "+ categoria.toUpperCase() + " hallados en The Books!");

            for(DataBooks dataBooks: filteredBooksByCategorie){
                System.out.println(indexBooksByCategories.getAndIncrement() + ". "+dataBooks);
            }

        } else {
            System.out.println("No existen libros disponibles para la categoria "+ categoria.toUpperCase());
        }
    }

    //Muestra las categorias disponibles en el enum
    public void showAllCategories() {
        AtomicInteger indexCategories = new AtomicInteger(1);

        System.out.println("Categorias disponibles en The Books!");
        DataBookshelves[] categorias = DataBookshelves.values();

        for (DataBookshelves categoria : categorias) {
            System.out.println(indexCategories.getAndIncrement() + ". " + categoria.name() + ": " + categoria.getBookshelves());
        }
    }



}
