package com.example.desafiobooks.entidades.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class BooksModel {
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
