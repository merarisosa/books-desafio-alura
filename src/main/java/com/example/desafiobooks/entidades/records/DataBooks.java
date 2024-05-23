package com.example.desafiobooks.entidades.records;

import com.example.desafiobooks.entidades.enums.DataBookshelves;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBooks(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAuthor> informacionAutor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas,
        @JsonAlias("bookshelves") List<String> bookshelves
) {
}
