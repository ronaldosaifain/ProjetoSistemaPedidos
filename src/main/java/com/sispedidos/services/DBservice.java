package com.sispedidos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sispedidos.domain.Categoria;
import com.sispedidos.domain.Cidade;
import com.sispedidos.domain.Cliente;
import com.sispedidos.domain.Endereco;
import com.sispedidos.domain.Estado;
import com.sispedidos.domain.ItemPedido;
import com.sispedidos.domain.Pagamento;
import com.sispedidos.domain.PagamentoComBoleto;
import com.sispedidos.domain.PagamentoComCartao;
import com.sispedidos.domain.Pedido;
import com.sispedidos.domain.Produto;
import com.sispedidos.domain.enums.EstadoPagamento;
import com.sispedidos.domain.enums.Perfil;
import com.sispedidos.domain.enums.TipoCliente;
import com.sispedidos.repository.CategoriaRepository;
import com.sispedidos.repository.CidadeRepository;
import com.sispedidos.repository.ClienteRepository;
import com.sispedidos.repository.EnderecoRepository;
import com.sispedidos.repository.EstadoRepository;
import com.sispedidos.repository.ItemPedidoRepository;
import com.sispedidos.repository.PagamentoRepository;
import com.sispedidos.repository.PedidoRepository;
import com.sispedidos.repository.ProdutoRepository;

@Service
public class DBservice {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itempedidoRepository;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
public void instantiateTestDatabase() throws ParseException {
	
	
	Categoria cat1 = new Categoria(null, "Informatica");
	Categoria cat2 = new Categoria(null, "Escritorio");
	Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
	Categoria cat4 = new Categoria(null, "Eletrônicos");
	Categoria cat5 = new Categoria(null, "Jardinagem");
	Categoria cat6 = new Categoria(null, "Decoração");
	Categoria cat7 = new Categoria(null, "Perfumaria");

	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "Impressora", 800.00);
	Produto p3 = new Produto(null, "Mouse", 80.00);
	Produto p4 = new Produto(null, "Mesa de Escriotório", 80.00);
	Produto p5 = new Produto(null, "Toalha", 80.00);
	Produto p6 = new Produto(null, "Colcha", 80.00);
	Produto p7 = new Produto(null, "TV true color", 80.00);
	Produto p8 = new Produto(null, "Roçadeira", 80.00);
	Produto p9 = new Produto(null, "Abajour", 80.00);
	Produto p10 = new Produto(null, "Pendente", 80.00);
	Produto p11= new Produto(null, "Shampoo", 80.00);

	cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
	cat2.getProdutos().addAll(Arrays.asList(p2,p4));
	cat3.getProdutos().addAll(Arrays.asList(p5,p6));
	cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
	cat5.getProdutos().addAll(Arrays.asList(p8));
	cat6.getProdutos().addAll(Arrays.asList(p9, p10));
	cat7.getProdutos().addAll(Arrays.asList(p11));


	p1.getCategoria().addAll(Arrays.asList(cat1,cat4));
	p2.getCategoria().addAll(Arrays.asList(cat1, cat2));
	p3.getCategoria().addAll(Arrays.asList(cat1,cat4));
	p4.getCategoria().addAll(Arrays.asList(cat2));
	p5.getCategoria().addAll(Arrays.asList(cat3));
	p6.getCategoria().addAll(Arrays.asList(cat3));
	p7.getCategoria().addAll(Arrays.asList(cat4));
	p8.getCategoria().addAll(Arrays.asList(cat5));
	p9.getCategoria().addAll(Arrays.asList(cat6));
	p10.getCategoria().addAll(Arrays.asList(cat6));
	p11.getCategoria().addAll(Arrays.asList(cat7));
	
	Estado est1 = new Estado(null, "Minas Gerais");
	Estado est2 = new Estado(null, "São Paulo");
	
	Cidade c1 = new Cidade(null, "Uberlandia", est1);
	Cidade c2 = new Cidade(null, "São Paulo", est2);
	Cidade c3 = new Cidade(null, "Campinas", est2);
	
	est1.getCidades().add(c1);
	est2.getCidades().addAll(Arrays.asList(c2,c3));
	
	
	
    estadoRepository.saveAll(Arrays.asList(est1, est2));
    cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
    
	categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7));
	produtoRepository.saveAll(Arrays.asList(p1, p2, p3,p4,p5,p6,p7,p8,p9,p10,p11));
	
	Cliente cli1 = new Cliente(null, "Maria Silva", "ronaldo100timo@gmail.com", "7171112", TipoCliente.PESSOAFISICA, pe.encode("123") );
	cli1.getTelefones().addAll(Arrays.asList("7171717", "727171717"));
	
	Cliente cli2 = new Cliente(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740", TipoCliente.PESSOAFISICA, pe.encode("123"));
	cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
	cli2.addPerfil(Perfil.ADMIN);
	
	Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "3848484", cli1, c1 );
	Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "2828237343", cli1,c2);
	
	cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
	
	clienteRepository.saveAll(Arrays.asList(cli1));
	enderecoRepository.saveAll(Arrays.asList(e1,e2));
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1 );
	Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 09:34"), cli1, e2 );
	
	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	ped1.setPagamento(pagto1);
	
	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2019 00:00"), null);
	ped2.setPagamento(pagto2);
	
	cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
	pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
	pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	
	
	ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
    ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
    
    ped1.getItens().addAll(Arrays.asList(ip1,ip2));
    ped2.getItens().addAll(Arrays.asList(ip3));
    
    p1.getItens().addAll(Arrays.asList(ip1));
    p2.getItens().addAll(Arrays.asList(ip3));
    p3.getItens().addAll(Arrays.asList(ip2));
    
    itempedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    	
	
}
	
}
