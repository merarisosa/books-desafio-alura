package com.example.desafiobooks.entidades.records;

import com.example.desafiobooks.entidades.modelos.BooksModel;
import com.example.desafiobooks.entidades.modelos.DataBooksModel;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Books(
        @JsonAlias("results") List<DataBooks> informacionLibros
) {

}
