package com.example.desafiobooks;

import com.example.desafiobooks.principal.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioBooksApplication implements CommandLineRunner {

	@Autowired
	private Menu menu = new Menu();

	public static void main(String[] args) {
		SpringApplication.run(DesafioBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Menu menu = new Menu();
		menu.initMenu();
	}
}
