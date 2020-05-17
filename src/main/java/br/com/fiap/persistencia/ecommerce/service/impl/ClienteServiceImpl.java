package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.CreateClienteDTO;
import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.repository.ClienteRepository;
import br.com.fiap.persistencia.ecommerce.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<ClienteDTO> findAll() {
		List<Cliente> clientesList = clienteRepository.findAll();
		return clientesList.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

	@Override
	public ClienteDTO findById(Integer id) {
		return new ClienteDTO(
				clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public ClienteDTO create(CreateClienteDTO createClienteDTO) {
		Cliente cliente = new Cliente(createClienteDTO);
		return new ClienteDTO(clienteRepository.save(cliente));
	}

	@Override
	public ClienteDTO update(Integer id, CreateClienteDTO createClienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(createClienteDTO.getNome());
		cliente.setEmail(createClienteDTO.getEmail());
		cliente.setTelefone(createClienteDTO.getTelefone());
		//cliente.setEnderecos(createClienteDTO.getEnderecos());
		return new ClienteDTO(clienteRepository.save(cliente));
	}

	@Override
	public void delete(Integer id) {
		clienteRepository.deleteById(id);
	}

}
