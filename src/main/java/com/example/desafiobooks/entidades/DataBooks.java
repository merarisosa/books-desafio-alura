package com.example.desafiobooks.entidades;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DataBooks(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAuthor> informacionAutor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas
) {
}
