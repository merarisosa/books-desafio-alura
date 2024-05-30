package com.example.desafiobooks.entidades.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBooks(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAuthor> informacionAutor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas,
        @JsonAlias("subjects") List<String> temas,
        @JsonAlias("bookshelves") List<String> bookshelves
) {

}
