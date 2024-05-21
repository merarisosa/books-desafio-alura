package com.example.desafiobooks.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatosToClass implements IConvierteDatosToClass{
    private ObjectMapper mapper =  new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String dataAPI, Class<T> clase) {
       try{
           return mapper.readValue(dataAPI.toString(), clase);
       }catch(JsonProcessingException e){
           throw new RuntimeException(e);
       }
    }
}
