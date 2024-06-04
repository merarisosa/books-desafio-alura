package com.example.desafiobooks.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MenuPrincipal {
    Scanner scInt = new Scanner(System.in);
    int respuesta;
    boolean flag = false;

    @Autowired
    MenuDB menuDB = new MenuDB();

    public void iniciarTheBooks() {
        do {
            showMenu();
            switch (respuesta) {
                case 1:
                    theBooksNoPersistente();
                    break;
                case 2:
                    theBooksPersistente();
                    break;
                case 0:
                    flag = true;
                    System.out.println("Gracias por usar The Books!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!flag);
    }

    public void showMenu(){
        System.out.println(" ");
        System.out.println("¡Bienvenido a The Books!");
        System.out.println("    Por favor, selecciona una opción del menu");
        System.out.println("        1. Utilizar The Books sin persistencia a objetos");
        System.out.println("        2. Utilizar The Books con persistencia a objetos");
        System.out.println("        0. Salir de The Books!");

        respuesta = scInt.nextInt();
    }

    public void theBooksNoPersistente(){
        MenuListas menu = new MenuListas();
        menu.initMenu();
    }

    public void theBooksPersistente(){
        menuDB.initMenu();
    }
}
