package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.modelos.BooksModel;
import com.example.desafiobooks.entidades.modelos.DataAuthorModel;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    //Guarda all books en la bd (nota: en la tabla solo asigna un autor a un libro, se pierden algunos)
    @Transactional
    public void saveAllBooksFromAPI() {
        daoAPI.showBooksAsClass();
        var size = daoAPI.dataBooksModelList.size();
        for (int i = 0; i < size; i++) {
            DataBooksModel book = daoAPI.dataBooksModelList.get(i);

            // Verificar si el libro ya está en la base de datos
            if (repoDataBooks.findByTitulo(book.getTitulo()).isPresent()) {
                System.out.println("Libro ya registrado en la base de datos: " + book.getTitulo());
                continue;
            }

            // Procesar los autores
            List<DataAuthorModel> processedAuthors = book.getInformacionAutor().stream()
                    .map(this::getOrCreateAuthor)
                    .collect(Collectors.toList());

            // Asignar los autores procesados al libro
            processedAuthors.forEach(author -> author.setLibro(book));
            book.setInformacionAutor(processedAuthors);

            // Guardar el libro con los autores
            repoDataBooks.save(book);
            System.out.println("Libro guardado en la base de datos: " + book.getTitulo());
        }
    }

    private DataAuthorModel getOrCreateAuthor(DataAuthorModel author) {
        return repoAuthor.findByNombre(author.getNombre())
                .orElseGet(() -> repoAuthor.save(author));
    }
    /*public void saveAllBooksFromAPI(){
        daoAPI.showBooksAsClass();
        var size = daoAPI.dataBooksModelList.size();
        for (int i = 0; i < size; i++) {
            DataBooksModel dataBooksModel = daoAPI.dataBooksModelList.get(i);
            //validando que si un libro no se encuentra en las categorias del enum, lo omita
         /*   if(!dataBooksModel.isShelveRepeated()) {
                System.out.println("Libro con categorías desconocidas, omitido: " + dataBooksModel.getTitulo());
                continue;
            }
            if (repoDataBooks.findByTitulo(dataBooksModel.getTitulo()).isPresent()) {
                System.out.println("Libro ya registrado en la base de datos: " + dataBooksModel.getTitulo());
            } else {
                if(dataBooksModel.getInformacionAutor().contains(repoDataBooks.findByTitulo(dataBooksModel.getInformacionAutor().toString()))){
                    //asignar al repoDataBooks el autor y guardar
                    repoDataBooks.save(dataBooksModel);
                    System.out.println("Libro guardado en la base de datos: " + dataBooksModel.getTitulo());


                }
            }
        }
    }*/

        //Buscar libro y guardarla en la base de datos
        public void lookingForBookToSave (String nombre){
            dao.lookingForBooksByName(nombre);
            DataBooks recordBook = dao.searchedBooks.get(0);
            DataBooksModel book = new DataBooksModel(recordBook);
            if (repoDataBooks.findByTitulo(recordBook.titulo()).isPresent()) {
                System.out.println("El libro " + book.getTitulo() + " ya ha sido añadido a la base de datos");
            } else {
                repoDataBooks.save(book);
            }
        }

        //Buscar las ultimas inserciones de busqueda en la bd
        @Transactional
        public void latestSearchesAsDatabase () {
            AtomicInteger indexLatestSearches = new AtomicInteger(1);
            List<DataBooksModel> allBooks = repoDataBooks.findAll();

            System.out.println("Últimas consultas en la base de datos:");
            if (!allBooks.isEmpty()) {
                allBooks.forEach(book -> {
                    System.out.println(indexLatestSearches.getAndIncrement() + ". " + book);
                });
            } else {
                System.out.println("No hay búsquedas recientes en la base de datos, consulta algo nuevo :).");
            }
        }

        //Mostrar todas las series buscadas

        //Buscar series por título
    //Top 5 mejores libros
    public void searchTopBooks(){
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataBooksModel>  topBooks = repoDataBooks.findTop5ByOrderByNumeroDescargasDesc();
        topBooks.forEach(book ->
                System.out.println(indexLatestSearches.getAndIncrement()
                        +". Libro: " + book.getTitulo()
                        + " --> Num. Descargas: " + book.getNumeroDescargas()));
    }


        //Buscar series por categorías
    public void lookingForBooksByCategorie(String categoria){
        AtomicInteger indexLatestSearches = new AtomicInteger(1);

        // Convertir la categoría del español al enum correspondiente
        DataBookshelves categorieToEnum;
        try {
            categorieToEnum = DataBookshelves.fromSpanish(categoria);
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría no válida: " + categoria);
            return;
        }

        List<DataBooksModel> libros = repoDataBooks.findByBookshelves(categorieToEnum);

        if (libros.isEmpty()) {
            System.out.println("No hay libros en la categoría " + categoria + ". Guarda libros en tu biblioteca :)");
        } else {
            System.out.println("Libros de la categoría " + categoria + ":");
            libros.forEach(book ->
                    System.out.println(indexLatestSearches.getAndIncrement() + ". " + book.getTitulo())
            );
        }
    }

        //Filtrar series por el número de temporadas y su evaluación

        //Buscar episodios por nombre

        //Top 5 episodios por serie

    }
