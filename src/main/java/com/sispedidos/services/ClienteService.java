package com.sispedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sispedidos.domain.Cidade;
import com.sispedidos.domain.Cliente;
import com.sispedidos.domain.Endereco;
import com.sispedidos.domain.enums.Perfil;
import com.sispedidos.domain.enums.TipoCliente;
import com.sispedidos.domain.security.UserSS;
import com.sispedidos.dto.ClienteDTO;
import com.sispedidos.dto.ClienteNewDTO;
import com.sispedidos.repository.ClienteRepository;
import com.sispedidos.repository.EnderecoRepository;
import com.sispedidos.service.exceptions.AuthorizationException;
import com.sispedidos.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
	
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}
	
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj =  repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;

	}
	

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);

	}

	public List<Cliente> findAll() {

		return repo.findAll();

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityViolationException("Não e possivel excluir uma cliente porque há pedidos relacionados");

		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String ordeBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), ordeBy);

		return repo.findAll(pageRequest);

	}

	 
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfoucnpj(),TipoCliente.toEnum(objDto.getTipo()),pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
	    Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(),cli, cid);
	    cli.getEnderecos().add(end);
	    cli.getTelefones().add(objDto.getTelefone1());
	    if(objDto.getTelefone2() != null) {
	    	cli.getTelefones().add(objDto.getTelefone2());	
	    }
	    if(objDto.getTelefone3() != null) {
	    	cli.getTelefones().add(objDto.getTelefone3());	
	    } 
	    return cli;
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
