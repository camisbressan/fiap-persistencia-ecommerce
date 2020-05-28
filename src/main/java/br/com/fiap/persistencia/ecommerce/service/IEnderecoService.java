package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public interface IEnderecoService {
	
	List<EnderecoDTO> findAllByCliente(Integer idCliente);

	EnderecoDTO create(Endereco endereco);

	EnderecoDTO update(Endereco endereco);

	void delete(Integer id);
}
