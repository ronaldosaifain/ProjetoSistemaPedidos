package com.sispedidos.sispedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sispedidos.services.DBservice;
import com.sispedidos.services.EmailService;
import com.sispedidos.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBservice dbservice;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatebase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}

		dbservice.instantiateTestDatabase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();

	}

}
