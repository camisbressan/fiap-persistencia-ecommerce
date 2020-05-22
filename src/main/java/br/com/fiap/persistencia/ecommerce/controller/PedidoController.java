package br.com.fiap.persistencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.entity.Pedido;
import br.com.fiap.persistencia.ecommerce.service.IClienteService;
import br.com.fiap.persistencia.ecommerce.service.IPedidoService;
import br.com.fiap.persistencia.ecommerce.service.IProdutoService;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IProdutoService prodService;
	
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> lista = pedidoService.findAll();
		return new ResponseEntity<List<Pedido>>(lista, lista.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pedido> findById(@PathVariable("id") Integer id) {
		Pedido p = pedidoService.findById(id);
		if (null != p) {
			return new ResponseEntity<Pedido>(p, HttpStatus.OK);
		}
		return new ResponseEntity<Pedido>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("cliente/{id}")
	public ResponseEntity<List<Pedido>> findByClienteId(@PathVariable("id") Integer id) {
		List<Pedido> l = pedidoService.findAllByCliente(id);
		return new ResponseEntity<List<Pedido>>(l, l.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
	// TODO Refazer para usar o DTO na entrada de Pedido com o Id Cliente e alterar a rota
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("cliente/{id}")
	public ResponseEntity<Pedido> create(@PathVariable Integer id, @RequestBody Pedido pedido, UriComponentsBuilder builder) {
		ClienteDTO cli = clienteService.findById(id);
		pedido.setCliente(new Cliente(cli));
		return ResponseEntity.ok(pedidoService.create(pedido));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		pedidoService.delete(id);
	}
		
}
