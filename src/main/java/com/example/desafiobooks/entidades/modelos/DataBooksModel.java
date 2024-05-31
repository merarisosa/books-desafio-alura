package com.example.desafiobooks.entidades.modelos;

import com.example.desafiobooks.entidades.records.DataAuthor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "data_books")
public class DataBooksModel {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
   @Column(unique = true)
    private String titulo;
   @Transient
    private List<DataAuthor> informacionAutor;
    @Transient
    private List<String> idiomas;
    private Double numeroDescargas;
    @Transient
    private List<String> temas;
    @Transient
    private List<String> bookshelves;

    public DataBooksModel(com.example.desafiobooks.entidades.records.DataBooks dataBooks) {
        this.titulo = dataBooks.titulo();
        this.informacionAutor = dataBooks.informacionAutor();
        this.idiomas = dataBooks.idiomas();
        this.numeroDescargas = dataBooks.numeroDescargas();
        this.temas = dataBooks.temas();
        this.bookshelves = dataBooks.bookshelves();
                /*.stream()
                .map(DataBookshelves::fromString)
                .collect(Collectors.toList());*/
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

    public List<DataAuthor> getInformacionAutor() {
        return informacionAutor;
    }

    public void setInformacionAutor(List<DataAuthor> informacionAutor) {
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

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
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
