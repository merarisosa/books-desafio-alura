package com.example.desafiobooks.entidades.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="books")
public class BooksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private List<DataBooksModel> informacionLibros;

    public BooksModel(List<DataBooksModel> informacionLibros) {
        this.informacionLibros = informacionLibros;
    }

    public List<DataBooksModel> getInformacionLibros() {
        return informacionLibros;
    }

    public void setInformacionLibros(List<DataBooksModel> informacionLibros) {
        this.informacionLibros = informacionLibros;
    }

    @Override
    public String toString() {
        return "Books as class{" +
                "informacionLibros=" + informacionLibros +
                '}';
    }
}
