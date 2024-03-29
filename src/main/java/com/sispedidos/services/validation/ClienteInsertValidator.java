package com.sispedidos.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sispedidos.domain.Cliente;
import com.sispedidos.domain.enums.TipoCliente;
import com.sispedidos.dto.ClienteNewDTO;
import com.sispedidos.repository.ClienteRepository;
import com.sispedidos.services.validation.utils.BR;
import com.sispedidos.sispedidos.resources.exception.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) & !BR.isValidCPF(objDto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj", "CPF INVALIDO"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) & !BR.isValidCPF(objDto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj", "CNPJ INVALIDO"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Esse email ja existe no banco de dados"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}