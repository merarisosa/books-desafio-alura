package com.example.desafiobooks.principal;

import com.example.desafiobooks.dao.DaoConsumoAPI;
import com.example.desafiobooks.dao.DaoMenuOpciones;
import com.example.desafiobooks.entidades.enums.DataBookshelves;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    int respuesta;
    boolean flag = false;
    Scanner scInt = new Scanner(System.in);
    Scanner scTxt = new Scanner(System.in);
    DaoMenuOpciones dao = new DaoMenuOpciones();

    public void initMenu(){
        do{
            showMenu();
            switch (respuesta){
                case 1:
                    showAPI();
                    break;
                case 2:
                    showBooksAsClass();
                    break;
                case 3:
                    showTopBooks();
                    break;
                case 4:
                    lookingForBooksByName();
                    break;
                case 5:
                    gettingStatistics();
                    break;
                case 6:
                    latestSearchesAsRecord();
                    break;
                case 7:
                    lookingForBooksByCategories();
                    break;
                case 8:

                    break;
                case 0:
                    flag = true;
                    System.out.println("Gracias por usar The Books!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }while(!flag);
    }

    public void showMenu(){
        System.out.println(" ");
        System.out.println("¡Bienvenido a The Books!");
        System.out.println("    Por favor, selecciona una opción del menu");
        System.out.println("        1. Consulta la API de libros disponibles como record");
        System.out.println("        2. Consulta la API de libros disponibles como clase");
        System.out.println("        3. Consulta el TOP 10 de libros más descargados");
        System.out.println("        4. Busca un libro por nombre");
        System.out.println("        5. Conoce las estadísticas de The Books!");
        System.out.println("        6. Consulta tu historial de búsquedas como record");
        System.out.println("        7. Consulta libros por categoria");
        System.out.println("        0. Salir de The Books!");

        respuesta = scInt.nextInt();
    }

    public void showAPI(){
        DaoConsumoAPI dao = new DaoConsumoAPI();
        try{
            dao.showBooksAsRecord();
        }catch (Exception e){
            System.out.println("Error (showDataBooks): "+ e);
        }
    }

    public void showBooksAsClass(){
        DaoConsumoAPI dao = new DaoConsumoAPI();
        try{
            dao.showBooksAsClass();
        }catch (Exception e){
            System.out.println("Error (showBooksAsClass): "+ e);
        }
    }

    public void showTopBooks(){
        try{
            dao.showTopBooks();
        }catch (Exception e){
            System.out.println("Error (showTopBooks): "+ e);
        }
    }

    public void lookingForBooksByName(){
        System.out.println("Introduce el nombre del libro que deseas consultar: ");
        var nombreBusqueda = scTxt.nextLine();

        try{
            dao.lookingForBooksByName(nombreBusqueda);
        }catch(InputMismatchException e){
            System.out.println("Error (lookingForBooksByName): "+ e);
        }
    }

    public void gettingStatistics(){
        try{
            dao.gettingStatistics();
        }catch(Exception e){
            System.out.println("Error (gettingStatistics): "+ e);
        }
    }

    public void latestSearchesAsRecord(){
        try{
            dao.latestSearches();
        }catch (Exception e){
            System.out.println("Error (latestSearchesAsRecord): "+ e);
        }
    }

    public void lookingForBooksByCategories(){
        try{
            System.out.println("Escribe el nombre de la categoria que deseas visualizar (Ej: HORROR): ");
            dao.showAllCategories();
            var categoria = scTxt.nextLine();
            var comparingValue = DataBookshelves.getNameOfEnum(categoria.toUpperCase().trim());

            if (categoria.equalsIgnoreCase(String.valueOf(comparingValue))){
                dao.lookingForBooksByCategories(comparingValue.getBookshelves());
            } else {
                System.out.println("Categoría inválida. Por favor, intente nuevamente.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Ingresa un número válido para la categoría.");
        } catch (Exception e) {
            System.out.println("Error (lookingForBooksByCategories): " + e);
        }
    }


}
