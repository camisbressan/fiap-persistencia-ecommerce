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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.dto.LoginDTO;
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

	/**
	 * 
	 * Métodos do Cliente
	 * 
	 */

	@GetMapping("{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(clienteService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente, UriComponentsBuilder builder) {
		return ResponseEntity.ok(clienteService.create(cliente));
	}

	@PutMapping("{id}")
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.update(cliente));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		clienteService.delete(id);
	}

	@GetMapping("login")
	public ResponseEntity<ClienteDTO> login(@RequestBody LoginDTO loginDTO) {
		return ResponseEntity.ok(clienteService.login(loginDTO));
	}

	/**
	 * 
	 * Métodos de Endereço do Cliente
	 * 
	 */

	@GetMapping("{id}/enderecos")
	public ResponseEntity<List<EnderecoDTO>> findAllEnderecoCliente(@PathVariable Integer id) {
		return ResponseEntity.ok(enderecoService.findAllByCliente(id));
	};

	@PostMapping("{id}/enderecos")
	public ResponseEntity<Void> adicionarEnderecoCliente(@PathVariable Integer id, @RequestBody Endereco endereco,
			UriComponentsBuilder builder) {
		ClienteDTO clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		endereco.setCliente(new Cliente(clienteRetorno));
		Endereco enderecoCriado = enderecoService.create(endereco);
		if (enderecoCriado != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("{id}/endereco/{endId")
					.buildAndExpand(clienteRetorno.getId(), enderecoCriado.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping("{id}/enderecos/{endId}")
	public ResponseEntity<Endereco> updateEnderecoCliente(@PathVariable("id") Integer id,
			@PathVariable("endId") Integer endId, @RequestBody Endereco endereco) {
		ClienteDTO clienteRetorno = clienteService.findById(id);
		if (clienteRetorno == null) {
			return new ResponseEntity<Endereco>(HttpStatus.NOT_ACCEPTABLE);
		}
		Endereco enderecoAlterado = enderecoService.update(endereco);
		if (enderecoAlterado != null) {
			return new ResponseEntity<Endereco>(enderecoAlterado, HttpStatus.OK);
		}
		return new ResponseEntity<Endereco>(HttpStatus.NO_CONTENT);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}/enderecos/{endId}")
	public void deletarEnderecoCliente(@PathVariable("id") Integer id, @PathVariable("endId") Integer endId) {
		enderecoService.delete(endId);
	}

}
