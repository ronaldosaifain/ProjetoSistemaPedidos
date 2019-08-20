package com.sispedidos.sispedidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.sispedidos.domain")
@ComponentScan("com.sispedidos.repository")
@ComponentScan("com.sispedidos.sispedidos.resources")
@ComponentScan("com.sispedidos.sispedidos.resources.exception")
@ComponentScan("com.sispedidos.services")
@ComponentScan("com.sispedidos.services.exceptions")

@EnableJpaRepositories("com.sispedidos.repository")
public class SistemaDePedidosApplication implements  CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SistemaDePedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		

	}

}
