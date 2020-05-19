package br.com.fiap.persistencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;
import br.com.fiap.persistencia.ecommerce.service.IClienteService;
import br.com.fiap.persistencia.ecommerce.service.IEnderecoService;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IEnderecoService enderecoService;

//	@Autowired
//	private IPedidoService pedidoServece;

	/**
	 * 
	 * Métodos do Cliente
	 * 
	 */

	@GetMapping("{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		if (null != cliente) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clienteList = clienteService.findAll();
		return new ResponseEntity<List<Cliente>>(clienteList,
				clienteList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		return ResponseEntity.ok(clienteService.create(cliente));
	}

	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.update(cliente));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCli(@PathVariable Integer id) {
		clienteService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 
	 * Métodos de Endereço do Cliente
	 * 
	 */

	@GetMapping("{id}/enderecos")
	public ResponseEntity<List<Endereco>> findAllEnderecoCliente(@PathVariable Integer id) {
		Cliente clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<List<Endereco>>(HttpStatus.NOT_ACCEPTABLE);
		}
		List<Endereco> listaEnderecos = enderecoService.findAllByCliente(clienteRetorno);
		return new ResponseEntity<List<Endereco>>(listaEnderecos,
				listaEnderecos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	};

	@PostMapping("{id}/enderecos")
	public ResponseEntity<Void> adicionarEnderecoCliente(@PathVariable Integer id, @RequestBody Endereco endereco,
			UriComponentsBuilder builder) {
		Cliente clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		endereco.setCliente(clienteRetorno);
		Endereco enderecoCriado = enderecoService.create(endereco);
		if (enderecoCriado != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("{id}/endereco/{endId")
					.buildAndExpand(clienteRetorno.getId(), enderecoCriado.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("{id}/enderecos/{endId}")
	public ResponseEntity<Endereco> findEnderecoCliente(@PathVariable("id") Integer id,
			@PathVariable("endId") Integer endId, @RequestBody Endereco endereco) {
		Cliente clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_ACCEPTABLE);
		}
		Endereco enderecoRetorno = enderecoService.findByIdCliente(clienteRetorno, endId);
		if (enderecoRetorno != null) {
			return new ResponseEntity<Endereco>(enderecoRetorno, HttpStatus.OK);
		}
		return new ResponseEntity<Endereco>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("{id}/enderecos/{endId}")
	public ResponseEntity<Endereco> updateEnderecoCliente(@PathVariable("id") Integer id,
			@PathVariable("endId") Integer endId, @RequestBody Endereco endereco) {
		Cliente clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_ACCEPTABLE);
		}
		Endereco enderecoCriado = enderecoService.update(endereco);
		if (enderecoCriado != null) {
			return new ResponseEntity<Endereco>(enderecoCriado, HttpStatus.OK);
		}
		return new ResponseEntity<Endereco>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{id}/enderecos/{endId}")
	public ResponseEntity<Void> deletarEnderecoCliente(@PathVariable("id") Integer id,
			@PathVariable("endId") Integer endId) {
		Cliente clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		Endereco enderecoCriado = enderecoService.findByIdCliente(clienteRetorno, endId);
		if (enderecoCriado == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		enderecoService.delete(enderecoCriado);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 
	 * Métodos de Pedido do Cliente
	 * 
	 */

//	@GetMapping("{id}/pedidos")
//	public ResponseEntity<List<Pedido>> getPedidoList(@PathVariable("id") int id) {
//
//		Cliente c = cliService.getById(id);
//		if (c == null) {
//			return new ResponseEntity<List<Pedido>>(HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		List<Pedido> l = pedidoServece.getAllByCliente(c);
//
//		return new ResponseEntity<List<Pedido>>(l, l.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
//
//	}
}
