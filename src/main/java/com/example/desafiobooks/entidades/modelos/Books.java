package com.example.desafiobooks.entidades.modelos;

import com.example.desafiobooks.entidades.records.DataBooks;

import java.util.List;

public class Books {
    private List<DataBooks> informacionLibros;

    public Books(List<DataBooks> informacionLibros) {
        this.informacionLibros = informacionLibros;
    }

    public List<DataBooks> getInformacionLibros() {
        return informacionLibros;
    }

    public void setInformacionLibros(List<DataBooks> informacionLibros) {
        this.informacionLibros = informacionLibros;
    }
}
