package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.modelos.BooksModel;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.Books;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import com.example.desafiobooks.service.ConsumoAPI;
import com.example.desafiobooks.service.ConvierteDatosToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoConsumoAPI {
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatosToClass convierteDatosToClass = new ConvierteDatosToClass();
    private String BASE_URL = "https://gutendex.com/books/";
    List<DataBooksModel> dataBooksModelList = new ArrayList<>();


    @Autowired
    private DataBooksRepository repoDataBooks;
    @Autowired
    private DataAuthorRepository repoAuthor;
    @Autowired
    private BookRepository repoBooks;

    public Books showDataFromAPI(){
        var data = consumoAPI.consumirAPI(BASE_URL);
        var dataToClass = convierteDatosToClass.obtenerDatos(data, Books.class);

        return dataToClass;
    }

    public void showBooksAsRecord(){
        System.out.println("*****************************************************");
        System.out.println("Data converted to record Books: ");
        System.out.println(showDataFromAPI());
        System.out.println("*****************************************************");
    }

    public void showBooksAsClass(){
       Books dataFromAPI = showDataFromAPI();
       var dataBooksList = dataFromAPI.informacionLibros();

        dataBooksModelList.clear();
       for (DataBooks dataBooks : dataBooksList) {
           DataBooksModel dataBooksModel = new DataBooksModel(dataBooks);
           dataBooksModelList.add(dataBooksModel);
           //repoDataBooks.save(dataBooksModel);
       }
        System.out.println("*****************************************************");
        System.out.println("Data converted to class Books: ");
        System.out.println(new BooksModel(dataBooksModelList));
        System.out.println("*****************************************************");
    }
}
