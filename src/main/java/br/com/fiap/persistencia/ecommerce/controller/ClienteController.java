package br.com.fiap.persistencia.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.fiap.persistencia.ecommerce.dto.CreateClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
	
	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> create(@RequestBody @Valid CreateClienteDTO createClienteDTO) {
		return ResponseEntity.ok(service.create(createClienteDTO));
	}

	@PutMapping("{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody CreateClienteDTO createClienteDTO){
        return ResponseEntity.ok(service.update(id, createClienteDTO));
    }

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}