package com.example.desafiobooks.entidades.modelos;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.example.desafiobooks.entidades.records.DataAuthor;

import java.util.List;
import java.util.stream.Collectors;

public class DataBooks {
   private String titulo;
    private List<DataAuthor> informacionAutor;
    private List<String> idiomas;
    private Double numeroDescargas;
    private List<DataBookshelves> bookshelfs;

    public DataBooks(com.example.desafiobooks.entidades.records.DataBooks dataBooks) {
        this.titulo = dataBooks.titulo();
        this.informacionAutor = dataBooks.informacionAutor();
        this.idiomas = dataBooks.idiomas();
        this.numeroDescargas = dataBooks.numeroDescargas();
        this.bookshelfs = dataBooks.bookshelves().stream()
                .map(DataBookshelves::fromString)
                .collect(Collectors.toList());
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

    public List<DataBookshelves> getBookshelfs() {
        return bookshelfs;
    }

    public void setBookshelfs(List<DataBookshelves> bookshelfs) {
        this.bookshelfs = bookshelfs;
    }

    @Override
    public String toString() {
        return "DataBooks{" +
                "titulo='" + titulo + '\'' +
                ", informacionAutor=" + informacionAutor +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas +
                ", bookshelfs=" + bookshelfs +
                '}';
    }
}
