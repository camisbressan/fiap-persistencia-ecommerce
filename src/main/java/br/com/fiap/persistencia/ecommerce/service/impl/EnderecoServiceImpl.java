package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
	@Cacheable(value = "allClienteEnderecosCache", key = "#idCliente", unless = "#result.size() == 0")
	public List<EnderecoDTO> findAllByCliente(Integer idCliente) {
		List<Endereco> endList = enderecoRepository.findAllByClienteId(idCliente);
		return endList.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}

	@Override
	@CacheEvict(cacheNames = "allClienteEnderecosCache", allEntries = true)
	public EnderecoDTO create(Endereco endereco) {
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}

	@Override
	@CacheEvict(cacheNames = "allClienteEnderecosCache", allEntries = true)
	public EnderecoDTO update(Endereco endereco) {
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "clienteCache", allEntries = true),
			@CacheEvict(value = "allClientesCache", allEntries = true),
			@CacheEvict(value = "allClienteEnderecosCache", allEntries = true) })
	public void delete(Integer id) {
		enderecoRepository.delete(enderecoRepository.findById(id).get());
	}
}
