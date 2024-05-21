package com.example.desafiobooks;

import com.example.desafiobooks.principal.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioBooksApplication {

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.initMenu();
	}

}
