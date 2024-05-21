package com.example.desafiobooks.entidades;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAuthor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento
        ) {
}
