package com.example.desafiobooks.service;

public interface IConvierteDatosToClass {
    <T> T obtenerDatos(String dataAPI, Class<T> clase);
}
