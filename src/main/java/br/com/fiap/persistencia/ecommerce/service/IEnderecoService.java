package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public interface IEnderecoService {
	
	List<Endereco> findAll();
	
	List<Endereco> findAllByCliente(Cliente cliente);

	Endereco findById(Integer id);
	
	Endereco findByIdCliente(Cliente cliente, Integer id);

	Endereco create(Endereco endereco);

	Endereco update(Endereco endereco);

	void delete(Endereco endereco);
}
