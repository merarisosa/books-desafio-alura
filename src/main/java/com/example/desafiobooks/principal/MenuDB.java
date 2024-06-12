package com.example.desafiobooks.principal;

import com.example.desafiobooks.dao.DaoConsumoAPI;
import com.example.desafiobooks.dao.DaoDatabaseOptions;
import com.example.desafiobooks.dao.DaoMenuOpciones;
import com.example.desafiobooks.repository.DataBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.InputMismatchException;
import java.util.List;
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

    @Autowired
    private DataBooksRepository repoDataBooks;

    public void initMenu(){
        do{
            showMenu();
            switch (respuesta){
                case 1:
                    //mostrar API
                    showAPI();
                    break;
                case 2:
                    //guardar libros de la API a la bd masivamente
                    saveAllBooksFromAPI();
                    break;
                case 3:
                    //guardar libros de la API a la bd inidividualmente
                    lookingForBookToSave();
                    break;
                case 4:
                    //ultimos registros en la bd
                    latestSearchesAsDatabase();
                    break;
                case 5:
                    //busca el top 5 de libros más descargados
                    searchTopBooks();
                    break;
                case 6:
                    //busca libros por categoria
                    lookingForBooksByCategorie();
                    break;
                case 7:
                    //busca libros por nombre
                    lookingForBooks();
                    break;
                case 8:
                    //listar autores registrados
                    showRegisteredAuthors();
                    break;
                case 9:
                    //listar autores vivos en un determinado año
                    showAuthorsAlive();
                    break;
                case 10:
                    //listar libros por idioma
                    showBooksByLanguage();
                    break;
                case 11:
                    //borra toda la database
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
        System.out.println("        8. Consulta los autores registrados ");
        System.out.println("        9. Consulta los autores vivos en un determinado año ");
        System.out.println("        10. Consulta libros por idioma ");
        System.out.println("        11. Elimina todos los registros de la base de datos");
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
        DaoMenuOpciones dao = new DaoMenuOpciones();
        dao.showAllCategories();
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

    public void showRegisteredAuthors(){
        try{
            daoDatabaseOptions.showRegisteredAuthors();
        }catch(Exception e){
            System.out.println("Error (showRegisteredAuthors): "+ e);
        }
    }

    public void showAuthorsAlive() {
        System.out.println("*************************************");
        System.out.println("Consulte autores dados ciertos años");
        System.out.println("*************************************");

        System.out.println("Ingrese el año de inicio: ");
        var dateInicio = scTxt.nextLine();
        System.out.println("Ingrese el año fin: ");
        var dateFin = scTxt.nextLine();

        try {
            daoDatabaseOptions.showAuthorsAlive(dateInicio, dateFin);
        } catch (Exception e) {
            System.out.println("Error (showAuthorsAlive): " + e);
        }
    }

    public void showBooksByLanguage() {
        System.out.println("Idiomas registrados en la base de datos");
        List<String> idiomas = repoDataBooks.findDistinctIdiomas();
        idiomas.forEach(System.out::println);

        System.out.println("Escribe el nombre del idioma a consultar: ");
        var idioma = scTxt.nextLine();

        try {
            daoDatabaseOptions.showBooksByLanguage(idioma);
        } catch (Exception e) {
            System.out.println("Error (showBooksByLanguage): " + e);
        }
    }

    public void deleteAllDatabase() {
        try {
            daoDatabaseOptions.deleteAllDatabase();
            System.out.println("Base de datos eliminada");
        } catch (Exception e) {
            System.out.println("Error (deleteAllDatabase): " + e);
        }
    }

}
