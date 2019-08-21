package com.sispedidos.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sispedidos.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void SendOrderConfimationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setTo(sender);
		sm.setSubject("Pedido Confirmado Codigo!:  " + obj.getId() );
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
		
	}
	
	
}
