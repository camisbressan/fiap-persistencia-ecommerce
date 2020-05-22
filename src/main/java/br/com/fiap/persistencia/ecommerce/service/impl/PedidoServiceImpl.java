package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.persistencia.ecommerce.entity.Pedido;
import br.com.fiap.persistencia.ecommerce.repository.PedidoRepository;
import br.com.fiap.persistencia.ecommerce.service.IPedidoService;

@Service
public class PedidoServiceImpl implements IPedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
//	@Cacheable(value= "allPedidosCache", unless= "#result.size() == 0")
	public List<Pedido> findAll() {
		List<Pedido> list = new ArrayList<>();
		pedidoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
//	@Cacheable(value= "pedidoCache", key= "#id")
	public Pedido findById(Integer id) {
		Optional<Pedido> opt = pedidoRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
//	@Cacheable(value= "allClientePedidosCache", key= "#cliente.id", unless= "#result.size() == 0")
	public List<Pedido> findAllByCliente(Integer id) {
		List<Pedido> list = new ArrayList<>();
		pedidoRepository.findPedidoByClienteId(id).forEach(e -> list.add(e));
		return list;
	}
	
	@Override	
//	@Caching(
//		put= { @CachePut(value= "pedidoCache", key= "#pedido.pedidoPK.codigo") },
//		evict= { 
//				@CacheEvict(value= "clienteCache", key="#pedido.cliente.id"),
//				@CacheEvict(value= "allClientesCache", allEntries= true),
//				@CacheEvict(value= "allPedidosCache", allEntries= true) 
//		}
//	)
	public Pedido create(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override	
//	@Caching(
//		put= { @CachePut(value= "pedidoCache", key= "#pedido.pedidoPK.codigo") },
//		evict= { 
//				@CacheEvict(value= "clienteCache", key="#pedido.cliente.id"),
//				@CacheEvict(value= "allClientesCache", allEntries= true),
//				@CacheEvict(value= "allPedidosCache", allEntries= true) 
//		}
//	)
	public Pedido update(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override	
//	@Caching(
//		evict= { 
//			@CacheEvict(value= "pedidoCache", key= "#pedido.pedidoPK.codigo"),
//			@CacheEvict(value= "clienteCache", key="#pedido.cliente.id"),
//			@CacheEvict(value= "allPedidosCache", allEntries= true)
//		}
//	)
	public void delete(Integer id) {
		pedidoRepository.delete(pedidoRepository.findById(id).get());
		
	}
	
}
