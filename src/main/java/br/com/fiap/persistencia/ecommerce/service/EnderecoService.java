package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.CreateEnderecoDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;

public interface EnderecoService {

	List<EnderecoDTO> findAll();

	EnderecoDTO findById(Integer id);

	EnderecoDTO create(CreateEnderecoDTO createEnderecoDTO);

	EnderecoDTO update(Integer id, CreateEnderecoDTO createEnderecoDTO);

	void delete(Integer id);
}