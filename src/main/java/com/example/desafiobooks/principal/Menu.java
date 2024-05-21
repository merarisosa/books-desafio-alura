package com.example.desafiobooks.principal;

import com.example.desafiobooks.dao.Dao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    int respuesta;
    boolean flag = false;
    Scanner scInt = new Scanner(System.in);
    Scanner scTxt = new Scanner(System.in);

    public void initMenu(){
        do{
            showMenu();
            switch (respuesta){
                case 1:
                    showAPI();
                    break;
                case 2:
                    showTopBooks();
                    break;
                case 3:
                    lookingForBooksByName();
                    break;
                case 4:
                    gettingStatistics();
                    break;
                case 5:
                    flag = true;
                    System.out.println("Gracias por usar The Books!");
                    break;
            }
        }while(!flag);
    }

    public void showMenu(){
        System.out.println(" ");
        System.out.println("¡Bienvenido a The Books!");
        System.out.println("    Por favor, selecciona una opción del menu");
        System.out.println("        1. Consulta la API de libros disponibles");
        System.out.println("        2. Consulta el TOP 10 de libros más descargados");
        System.out.println("        3. Busca un libro por nombre");
        System.out.println("        4. Conoce las estadísticas de The Books!");
        System.out.println("        5. Salir de The Books!");

        respuesta = scInt.nextInt();
    }

    public void showAPI(){
        Dao dao = new Dao();
        try{
            dao.verAPI();
        }catch (Exception e){
            System.out.println("Error (showDataBooks): "+ e);
        }
    }

    public void showTopBooks(){
        Dao dao = new Dao();
        try{
            dao.showTopBooks();
        }catch (Exception e){
            System.out.println("Error (showTopBooks): "+ e);
        }
    }

    public void lookingForBooksByName(){
        System.out.println("Introduce el nombre del libro que deseas consultar: ");
        var nombreBusqueda = scTxt.nextLine();

        Dao dao = new Dao();
        try{
            dao.lookingForBooksByName(nombreBusqueda);
        }catch(InputMismatchException e){
            System.out.println("Error (lookingForBooksByName): "+ e);
        }
    }

    public void gettingStatistics(){
        Dao dao = new Dao();
        try{
            dao.gettingStatistics();
        }catch(Exception e){
            System.out.println("Error (gettingStatistics): "+ e);
        }
    }
}
