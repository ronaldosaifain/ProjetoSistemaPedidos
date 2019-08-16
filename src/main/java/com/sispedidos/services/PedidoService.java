package com.sispedidos.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sispedidos.domain.ItemPedido;
import com.sispedidos.domain.PagamentoComBoleto;
import com.sispedidos.domain.Pedido;
import com.sispedidos.domain.enums.EstadoPagamento;
import com.sispedidos.repository.ItemPedidoRepository;
import com.sispedidos.repository.PagamentoRepository;
import com.sispedidos.repository.PedidoRepository;
import com.sispedidos.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

	  @Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
		PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();	
		boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());	
		}
      
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.SetPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
