package com.example.desafiobooks.dao;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.modelos.DataAuthorModel;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.example.desafiobooks.entidades.records.DataBooks;
import com.example.desafiobooks.repository.BookRepository;
import com.example.desafiobooks.repository.DataAuthorRepository;
import com.example.desafiobooks.repository.DataBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    //Buscar libro y guardarla en la base de datos
    public void lookingForBookToSave(String nombre) {
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
    public void latestSearchesAsDatabase() {
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

    //Top 5 mejores libros
    public void searchTopBooks() {
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataBooksModel> topBooks = repoDataBooks.findTop5ByOrderByNumeroDescargasDesc();
        topBooks.forEach(book ->
                System.out.println(indexLatestSearches.getAndIncrement()
                        + ". Libro: " + book.getTitulo()
                        + " --> Num. Descargas: " + book.getNumeroDescargas()));
    }

    //Buscar series por categorías
    public void lookingForBooksByCategorie(String categoria) {
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
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

    //Buscar libros por nombre
    public void lookingForBooks(String nombreLibro) {
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataBooksModel> librosBuscados = repoDataBooks.librosPorNombre(nombreLibro);

        if (librosBuscados.isEmpty()) {
            System.out.println("No existen libros en la base de datos con el nombre " + nombreLibro.toUpperCase());
        } else {
            System.out.println("Libros que coinciden con el nombre de búsqueda");
            librosBuscados.forEach(l ->
                    System.out.println(indexLatestSearches.getAndIncrement() + ". " + l.getTitulo()));
        }
    }

    //busca libros segun el idioma
    public void showBooksByLanguage(String idioma) {
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataBooksModel> librosByLanguage = repoDataBooks.findByIdiomas(idioma);
        librosByLanguage.forEach(i -> System.out.println(indexLatestSearches.getAndIncrement() + "." + i.getTitulo().replace(",", " ")));
    }

    //muestra los autores vivos en un determinado año
    public void showAuthorsAlive(String nacimiento, String muerte) {
        AtomicInteger indexLatestSearches = new AtomicInteger(1);
        List<DataAuthorModel> autoresVivos = repoAuthor.findByFechaAlive(nacimiento, muerte);

        if (autoresVivos.isEmpty()) {
            System.out.println("No existen autores en el año brindado");
        } else {
            autoresVivos.forEach(a ->
                    System.out.println(indexLatestSearches.getAndIncrement() + ". " + a.getNombre()));
        }
    }

    //busca el listado de autores en la bd
    public void showRegisteredAuthors() {
        List<DataAuthorModel> autores = repoAuthor.findAll();

        if (autores.isEmpty()) {
            System.out.println("No existen autores registrados en la base de datos");
        } else {
            for (int i = 0; i < autores.size(); i++) {
                System.out.println("--- Autor ---");
                System.out.println("Nombre: " + autores.get(i).getNombre());
                System.out.println("Fecha de nacimiento: " + autores.get(i).getFechaNacimiento());
                System.out.println("Fecha de muerte: " + autores.get(i).getFechaMuerte());
                System.out.println("-------------");
            }
        }
    }

    //borra toda la base de datos
    public void deleteAllDatabase() {
        repoDataBooks.deleteAll();
        repoAuthor.deleteAll();
        repoBooks.deleteAll();
    }

}
