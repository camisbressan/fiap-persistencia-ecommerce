package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.persistencia.ecommerce.dto.PedidoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Pedido;
import br.com.fiap.persistencia.ecommerce.repository.PedidoRepository;
import br.com.fiap.persistencia.ecommerce.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	@Cacheable(value = "allPedidosCache", unless = "#result.size() == 0")
	public List<PedidoDTO> findAll() {
		List<Pedido> pedidoList = pedidoRepository.findAll();
		return pedidoList.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "pedidoCache", key = "#id")
	public PedidoDTO findById(Integer id) {
		return new PedidoDTO(
				pedidoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	@Cacheable(value = "allClientePedidosCache", key = "#id", unless = "#result.size() == 0")
	public List<PedidoDTO> findAllByCliente(Integer id) {
		List<Pedido> pedidoList = pedidoRepository.findPedidoByClienteId(id);
		return pedidoList.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "pedidoCache", allEntries = true),
			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "pedidoCache", key = "#id"),
			@CacheEvict(value = "allPedidosCache", allEntries = true) })
	public void delete(Integer id) {
		pedidoRepository.delete(pedidoRepository.findById(id).get());

	}

}
