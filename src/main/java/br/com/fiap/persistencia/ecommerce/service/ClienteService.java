package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.CreateClienteDTO;

public interface ClienteService {

	List<ClienteDTO> findAll();

	ClienteDTO findById(Integer id);

	ClienteDTO create(CreateClienteDTO createClienteDTO);

	ClienteDTO update(Integer id, CreateClienteDTO createClienteDTO);

	void delete(Integer id);
}