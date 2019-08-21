package com.sispedidos.sispedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sispedidos.services.DBservice;
import com.sispedidos.services.EmailService;
import com.sispedidos.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBservice dbservice;

	@Bean
	public boolean instantiateDatebase() throws ParseException {

		dbservice.instantiateTestDatabase();

		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
