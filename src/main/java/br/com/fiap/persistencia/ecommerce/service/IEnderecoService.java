package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public interface IEnderecoService {
	
	List<EnderecoDTO> findAll();
	
	List<EnderecoDTO> findAllByCliente(Integer idCliente);

	EnderecoDTO findById(Integer id);
	
	EnderecoDTO findByIdCliente(Integer idCliente, Integer id);

	Endereco create(Endereco endereco);

	Endereco update(Endereco endereco);

	void delete(Integer id);
}
