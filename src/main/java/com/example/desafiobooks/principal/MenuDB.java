package com.example.desafiobooks.principal;

import com.example.desafiobooks.dao.DaoConsumoAPI;
import com.example.desafiobooks.dao.DaoDatabaseOptions;
import com.example.desafiobooks.dao.DaoMenuOpciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class MenuDB {
    int respuesta;
    boolean flag = false;
    Scanner scInt = new Scanner(System.in);
    Scanner scTxt = new Scanner(System.in);

    @Autowired
    DaoMenuOpciones dao = new DaoMenuOpciones();

    @Autowired
    DaoDatabaseOptions daoDatabaseOptions = new DaoDatabaseOptions();

    @Autowired
    DaoConsumoAPI daoConsumoAPI = new DaoConsumoAPI();

    public void initMenu(){
        do{
            showMenu();
            switch (respuesta){
                case 1:
                    showAPI();
                    break;
                case 2:
                    saveAllBooksFromAPI();
                    break;
                case 3:
                    lookingForBookToSave();
                    break;
                case 4:
                    latestSearchesAsDatabase();
                    break;
                case 5:
                    searchTopBooks();
                    break;
                case 6:
                    lookingForBooksByCategorie();
                    break;
                case 7:
                    lookingForBooks();
                    break;
                case 8:
                    break;
                case 9:
                    deleteAllDatabase();
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
        System.out.println("        1. Consulta la API de libros disponibles");
        System.out.println("        2. Guarda la lista de libros en la base de datos");
        System.out.println("        3. Consulta un libro y guardalo en la base de datos");
        System.out.println("        4. Consulta las últimas búsquedas que has realizado en la base de datos");
        System.out.println("        5. Consulta el TOP 5 de libros con más descargas ");
        System.out.println("        6. Consulta libros por categoria ");
        System.out.println("        7. Consulta un libro por nombre ");
        System.out.println("        8.  ");
        System.out.println("        9. Elimina todos los registros de la base de datos");
        System.out.println("        0. Regresar al menú principal");

        respuesta = scInt.nextInt();
    }

    public void showAPI(){
        try{
            daoConsumoAPI.showBooksAsRecord();
        }catch (Exception e){
            System.out.println("Error (showDataBooks): "+ e);
        }
    }

    public void lookingForBookToSave(){
        System.out.println("Introduce el nombre del libro que deseas consultar: ");
        var nombreBusqueda = scTxt.nextLine();
        try{
            daoDatabaseOptions.lookingForBookToSave(nombreBusqueda);
        }catch(InputMismatchException e){
            System.out.println("Error (lookingForBookToSave): "+ e);
        }
    }

    public void saveAllBooksFromAPI(){
        try{
            daoDatabaseOptions.saveAllBooksFromAPI();
        }catch (Exception e){
            System.out.println("Error (saveAllBooksFromAPI): "+ e);
        }
    }

    public void latestSearchesAsDatabase(){
        System.out.println("TOP 5 de libros más descargados");
        try{
            daoDatabaseOptions.latestSearchesAsDatabase();
        }catch (Exception e){
            System.out.println("Error (latestSearchesAsDatabase): "+ e);
        }
    }

    public void searchTopBooks(){
        try{
            daoDatabaseOptions.searchTopBooks();
        }catch (Exception e){
            System.out.println("Error (searchTopBooks): "+ e);
        }
    }

    public void lookingForBooksByCategorie(){
        System.out.println("Escriba la categoria de la cual desea buscar un libro");
        var categoria = scTxt.nextLine();
        try{
            daoDatabaseOptions.lookingForBooksByCategorie(categoria);
        }catch (Exception e){
            System.out.println("Error (lookingForBooksByCategorie): "+ e);
        }
    }

    public void lookingForBooks(){
        System.out.println("Ingrese el nombre del libro a consultar");
        var nombreLibro = scTxt.next();
        try{
            daoDatabaseOptions.lookingForBooks(nombreLibro);
        }catch(Exception e){
            System.out.println("Error (lookingForBooks): "+ e);
        }
    }
    public void deleteAllDatabase(){

    }

}
