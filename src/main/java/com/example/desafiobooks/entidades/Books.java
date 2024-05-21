package com.example.desafiobooks.entidades;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Books(
        @JsonAlias("results") List<DataBooks> informacionLibros
) {
}
