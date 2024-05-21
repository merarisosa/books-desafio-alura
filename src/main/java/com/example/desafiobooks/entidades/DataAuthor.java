package com.example.desafiobooks.entidades;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento
        ) {
}
