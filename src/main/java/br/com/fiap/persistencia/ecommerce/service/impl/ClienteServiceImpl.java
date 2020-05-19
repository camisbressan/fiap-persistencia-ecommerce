package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.repository.ClienteRepository;
import br.com.fiap.persistencia.ecommerce.service.IClienteService;

@Service
public class ClienteServiceImpl  implements IClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
//	@Cacheable(value= "allClientesCache", unless= "#result.size() == 0")	
	public List<Cliente> findAll() {
		List<Cliente> list = new ArrayList<>();
		clienteRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override	
//	@Cacheable(value= "clienteCache", key= "#id")	
	public Cliente findById(Integer id) {
		Optional<Cliente> opt = clienteRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override	
//	@Caching(
//		put= { @CachePut(value= "clienteCache", key= "#cliente.id") },
//		evict= { @CacheEvict(value= "allClientesCache", allEntries= true) }
//	)
	public Cliente create(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override	
//	@Caching(
//		put= { @CachePut(value= "clienteCache", key= "#cliente.id") },
//		evict= { @CacheEvict(value= "allClientesCache", allEntries= true) }
//	)
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override	
//	@Caching(
//		evict= { 
//			@CacheEvict(value= "clienteCache", key= "#id"),
//			@CacheEvict(value= "allClientesCache", allEntries= true)
//		}
//	)
	public void delete(Integer id) {
		clienteRepository.delete(clienteRepository.findById(id).get());	
	}
		
}
