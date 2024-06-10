package com.example.desafiobooks.entidades.modelos;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.records.DataAuthor;
import com.example.desafiobooks.entidades.records.DataBooks;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "data_books")
public class DataBooksModel {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

   @Column(unique = true)
    private String titulo;

   //cambiar a many to many
   @OneToMany (mappedBy = "libro", cascade = CascadeType.ALL) //un libro pertence a muchos autores
    private List<DataAuthorModel> informacionAutor;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "data_books_id"))
    @Column(name = "idioma", nullable = false)
    private List<String> idiomas;

    private Double numeroDescargas;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "temas", joinColumns = @JoinColumn(name = "data_books_id"))
    @Column(name = "tema", nullable = false)
    private List<String> temas;

    @Enumerated (EnumType.STRING )
    @ElementCollection(targetClass = DataBookshelves.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "bookshelves", joinColumns = @JoinColumn(name = "data_books_id"))
    @Column(name = "bookshelf", nullable = false)
    private List<DataBookshelves> bookshelves;

    @ManyToOne
    private BooksModel libreria;

    public DataBooksModel(){

    }

    public DataBooksModel(DataBooks dataBooks) {
        this.titulo = dataBooks.titulo();
        List<DataAuthor> autores = dataBooks.informacionAutor();
        List<DataAuthorModel> autoresModel = autores.stream()
                .map(DataAuthorModel::fromDataAuthor)
                .collect(Collectors.toList());
        this.informacionAutor = autoresModel;
        this.idiomas = dataBooks.idiomas();
        this.numeroDescargas = dataBooks.numeroDescargas();
        this.temas = dataBooks.temas();
        this.bookshelves = dataBooks.bookshelves().stream()
                .map(DataBookshelves::fromString)
                .collect(Collectors.toList());
               /* .map(b -> DataBookshelves.getNameOfEnum(b))
                .toList();

                */
    }

    //MAPEAR DIRECCIONALMENTE
    public void addAuthor(DataAuthorModel authorModel) {
        this.informacionAutor.add(authorModel);
        authorModel.setLibro(this);
    }

    @NotNull
    public static DataAuthorModel fromDataAuthor(DataAuthor dataAuthor) {
        DataAuthorModel authorModel = new DataAuthorModel();
        authorModel.setNombre(dataAuthor.nombre());
        authorModel.setFechaNacimiento(dataAuthor.fechaNacimiento());
        //authorModel.setLibro();
        //authorModel.setId();
        return authorModel;
    }
    /*
    public DataBooksModel(com.example.desafiobooks.entidades.records.DataBooks dataBooks) {
        this.titulo = dataBooks.titulo();
        this.informacionAutor = dataBooks.informacionAutor();
        this.idiomas = dataBooks.idiomas();
        this.numeroDescargas = dataBooks.numeroDescargas();
        this.temas = dataBooks.temas();
        this.bookshelves = dataBooks.bookshelves();
                /*.stream()
                .map(DataBookshelves::fromString)
                .collect(Collectors.toList());
    }
*/

    public BooksModel getLibreria() {
        return libreria;
    }

    public void setLibreria(BooksModel libreria) {
        this.libreria = libreria;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DataAuthorModel> getInformacionAutor() {
        return informacionAutor;
    }

    public void setInformacionAutor(List<DataAuthorModel> informacionAutor) {
        informacionAutor.forEach(a -> a.setLibro(this));
        this.informacionAutor = informacionAutor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    public List<DataBookshelves> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<DataBookshelves> bookshelves) {
        this.bookshelves = bookshelves;
    }



    @Override
    public String toString() {
        return "DataBooks as class{" +
                "titulo='" + titulo + '\'' +
                ", informacionAutor=" + informacionAutor +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas +
                ", bookshelfs=" + bookshelves +
                ", temas=" + temas +
                '}';
    }
}

