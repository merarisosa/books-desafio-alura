package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.Books;
import com.example.desafiobooks.entidades.DataBooks;
import com.example.desafiobooks.service.ConsumoAPI;
import com.example.desafiobooks.service.ConvierteDatosToClass;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Dao {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatosToClass convierteDatosToClass = new ConvierteDatosToClass();
    private String BASE_URL = "https://gutendex.com/books/";

    //Obtener los datos de la API para manipular en formato json y en formato class
    public Books showDataFromAPI(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, Books.class);

        return dataToClass;
    }

    //Ver la API como clase Books
    public Books verAPI(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, Books.class);
        System.out.println("*****************************************************");
        System.out.println("Data converted to class Books: ");
        System.out.println(dataToClass);
        System.out.println("*****************************************************");

        return dataToClass;
    }

    //Top 10 libros más descargados
    public void showTopBooks(){
        AtomicInteger index = new AtomicInteger(1); // Inicializamos un AtomicInteger para el índice
        System.out.println("Top 10 libros más descargados");
        showDataFromAPI().informacionLibros().stream()
                .sorted(Comparator.comparing(DataBooks::numeroDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(
                        book -> {
                        System.out.println(index.getAndIncrement() + ". " + book);
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
}
