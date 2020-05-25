package br.com.fiap.persistencia.ecommerce.controller;

import java.util.Date;
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

import br.com.fiap.persistencia.ecommerce.dto.CreatePedidoDTO;
import br.com.fiap.persistencia.ecommerce.dto.ItensCarrinhoDTO;
import br.com.fiap.persistencia.ecommerce.dto.PedidoDTO;
import br.com.fiap.persistencia.ecommerce.entity.ItensCarrinho;
import br.com.fiap.persistencia.ecommerce.entity.Pedido;
import br.com.fiap.persistencia.ecommerce.service.IPedidoService;
import br.com.fiap.persistencia.ecommerce.service.IProdutoService;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;

	@Autowired
	private IProdutoService prodService;


	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		return ResponseEntity.ok(pedidoService.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(pedidoService.findById(id));
	}

	@GetMapping("cliente/{id}")
	public ResponseEntity<List<PedidoDTO>> findByClienteId(@PathVariable("id") Integer id) {
		List<PedidoDTO> l = pedidoService.findAllByCliente(id);
		return new ResponseEntity<List<PedidoDTO>>(l, l.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}

	@PostMapping("cliente/{id}")
	public ResponseEntity<Pedido> create(@PathVariable Integer id, @RequestBody CreatePedidoDTO createPedidoDTO,
			UriComponentsBuilder builder) {
		for (ItensCarrinhoDTO itensDTO : createPedidoDTO.getItens()) {
			pedidoService.create(new Pedido(id, createPedidoDTO.getValorTotal(), new Date(),
					new ItensCarrinho(prodService.findById(itensDTO.getProduto().getId()), itensDTO.getQuantidade())));
		}
		return new ResponseEntity<Pedido>(HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		pedidoService.delete(id);
	}

}
