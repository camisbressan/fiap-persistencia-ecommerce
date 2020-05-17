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

import br.com.fiap.persistencia.ecommerce.dto.CreateProdutoDTO;
import br.com.fiap.persistencia.ecommerce.dto.ProdutoDTO;
import br.com.fiap.persistencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {
	
	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid CreateProdutoDTO createProdutoDTO) {
		return ResponseEntity.ok(service.create(createProdutoDTO));
	}

	@PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody CreateProdutoDTO createProdutoDTO){
        return ResponseEntity.ok(service.update(id, createProdutoDTO));
    }

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}