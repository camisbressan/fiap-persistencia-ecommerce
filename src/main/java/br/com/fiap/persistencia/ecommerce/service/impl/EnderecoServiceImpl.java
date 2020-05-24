package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;
import br.com.fiap.persistencia.ecommerce.repository.EnderecoRepository;
import br.com.fiap.persistencia.ecommerce.service.IEnderecoService;

@Service
public class EnderecoServiceImpl implements IEnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
//	@Cacheable(value= "allEnderecosCache", unless= "#result.size() == 0")
	public List<EnderecoDTO> findAll() {
		List<Endereco> endList = enderecoRepository.findAll();
		return endList.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}
	
	@Override
//	@Cacheable(value= "allClienteEnderecosCache", key= "#cliente.id", unless= "#result.size() == 0")
	public List<EnderecoDTO> findAllByCliente(Integer idCliente) {
		List<Endereco> endList = enderecoRepository.findAll();
		return endList.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}

	@Override
//	@Cacheable(value= "enderecoCache", key= "#id")
	public EnderecoDTO findById(Integer id) {
		return new EnderecoDTO(enderecoRepository.findById(id).get());
	}
	
	@Override
//	@Cacheable(value= "enderecoCache", key= "#id")
	public EnderecoDTO findByIdCliente(Integer idCliente, Integer id) {
		return new EnderecoDTO(
				enderecoRepository.findEnderecoByClienteId(idCliente, id));
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
	public void delete(Integer id) {
		enderecoRepository.delete(enderecoRepository.findById(id).get());
	}
}
