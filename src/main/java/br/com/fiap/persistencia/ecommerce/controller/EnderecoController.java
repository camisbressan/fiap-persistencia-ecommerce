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

import br.com.fiap.persistencia.ecommerce.dto.CreateEnderecoDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/v1/enderecos")
public class EnderecoController {

	private final EnderecoService service;

	public EnderecoController(EnderecoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EnderecoDTO> create(@RequestBody @Valid CreateEnderecoDTO createEnderecoDTO) {
		return ResponseEntity.ok(service.create(createEnderecoDTO));
	}

	@PutMapping("{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Integer id, @RequestBody CreateEnderecoDTO createEnderecoDTO){
        return ResponseEntity.ok(service.update(id, createEnderecoDTO));
    }

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}