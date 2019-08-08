package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.service.exceptions.*;
import com.cursomc.domain.Cliente;
import com.cursomc.repository.ClienteRepository;

@Service 
public class ClienteService {

@Autowired
private ClienteRepository repo;	
	
public Cliente buscar(Integer id) {
	
	Optional<Cliente> obj = repo.findById(id);
	return obj.orElseThrow(() -> new ObjectNotFoundException(
	"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	
	
}
}