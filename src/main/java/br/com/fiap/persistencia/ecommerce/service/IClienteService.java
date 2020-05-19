package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.entity.Cliente;

public interface IClienteService {
	
//	List<Cliente> findAll();
	List<ClienteDTO> findAll();
	
	Cliente findById(Integer id);

	Cliente create(Cliente cliente);

	Cliente update(Cliente cliente);

	void delete(Integer id);
}
