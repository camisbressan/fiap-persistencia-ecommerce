package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;
import br.com.fiap.persistencia.ecommerce.repository.EnderecoRepository;
import br.com.fiap.persistencia.ecommerce.service.IEnderecoService;

@Service
public class EnderecoServiceImpl implements IEnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
//	@Cacheable(value= "allEnderecosCache", unless= "#result.size() == 0")
	public List<Endereco> findAll() {
		List<Endereco> list = new ArrayList<>();
		enderecoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
//	@Cacheable(value= "allClienteEnderecosCache", key= "#cliente.id", unless= "#result.size() == 0")
	public List<Endereco> findAllByCliente(Cliente cliente) {
		List<Endereco> list = new ArrayList<>();
		enderecoRepository.findAllByClienteId(cliente.getId()).forEach(e -> list.add(e));
		return list;
	}

	@Override
//	@Cacheable(value= "enderecoCache", key= "#id")
	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id).get();
	}
	
	@Override
//	@Cacheable(value= "enderecoCache", key= "#id")
	public Endereco findByIdCliente(Cliente cliente, Integer id) {
		return enderecoRepository.findEnderecoByClienteId(cliente.getId(), id);
	}

	@Override	
//	@Caching(
//		put= { @CachePut(value= "enderecoCache", key= "#endereco.id") },
//				evict= { 
//						@CacheEvict(value= "clienteCache", key="#endereco.cliente.id"),
//						@CacheEvict(value= "allClientesCache", allEntries= true),
//						@CacheEvict(value= "allEnderecosCache", allEntries= true)
//				}
//	)
	public Endereco create(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	@Override	
//	@Caching(
//		put= { @CachePut(value= "enderecoCache", key= "#endereco.id") },
//		evict= { 
//				@CacheEvict(value= "clienteCache", key="#endereco.cliente.id"),
//				@CacheEvict(value= "allClientesCache", allEntries= true),
//				@CacheEvict(value= "allEnderecosCache", allEntries= true)
//		}
//	)
	public Endereco update(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	@Override	
//	@Caching(
//		evict= { 
//			@CacheEvict(value= "enderecoCache", key= "#endereco.id"),
//			@CacheEvict(value= "clienteCache", key="#endereco.cliente.id"),		
//			@CacheEvict(value= "allClientesCache", allEntries= true),
//			@CacheEvict(value= "allEnderecosCache", allEntries= true)
//		}
//	)
	public void delete(Endereco endereco) {
		enderecoRepository.delete(endereco);	
	}
}
