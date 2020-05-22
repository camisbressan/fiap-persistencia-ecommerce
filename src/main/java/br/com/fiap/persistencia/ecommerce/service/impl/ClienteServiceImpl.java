package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.LoginDTO;
import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.repository.ClienteRepository;
import br.com.fiap.persistencia.ecommerce.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
//	@Cacheable(value= "allClientesCache", unless= "#result.size() == 0")	
	public List<ClienteDTO> findAll() {
		List<Cliente> clienteList = clienteRepository.findAll();
		return clienteList.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

	@Override
//	@Cacheable(value= "clienteCache", key= "#id")	
	public ClienteDTO findById(Integer id) {
		return new ClienteDTO(
				clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
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

	@Override
	public ClienteDTO login(LoginDTO login) {
		return new ClienteDTO(clienteRepository.findClienteByLogin(login.getEmail(), login.getSenha()));
	}

}
