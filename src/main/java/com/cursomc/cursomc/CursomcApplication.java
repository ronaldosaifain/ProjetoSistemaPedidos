package com.cursomc.cursomc;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.cursomc.domain.Categoria;
import com.cursomc.repository.CategoriaRepository;

@SpringBootApplication
@EntityScan("com.cursomc.domain")
public class CursomcApplication implements CommandLineRunner{

private CategoriaRepository categoriaRepository;	
	
	public static void main(String[] args)  {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
	  categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
		
		
	}

}
