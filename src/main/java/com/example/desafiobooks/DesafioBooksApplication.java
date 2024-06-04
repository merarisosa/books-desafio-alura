package com.example.desafiobooks;

import com.example.desafiobooks.principal.MenuListas;

import com.example.desafiobooks.principal.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioBooksApplication implements CommandLineRunner {

	@Autowired
	private MenuPrincipal menu = new MenuPrincipal();

	public static void main(String[] args) {
		SpringApplication.run(DesafioBooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.iniciarTheBooks();
	}
}
