package com.sispedidos.services;

import org.springframework.mail.SimpleMailMessage;

import com.sispedidos.domain.Pedido;

public interface EmailService {

	void SendOrderConfimationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
