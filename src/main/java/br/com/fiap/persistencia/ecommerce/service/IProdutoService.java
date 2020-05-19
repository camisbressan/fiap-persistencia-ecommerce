package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.CreateProdutoDTO;
import br.com.fiap.persistencia.ecommerce.dto.ProdutoDTO;

public interface IProdutoService {

	List<ProdutoDTO> findAll();

	ProdutoDTO findById(Integer id);

	ProdutoDTO create(CreateProdutoDTO createProdutoDTO);

	ProdutoDTO update(Integer id, CreateProdutoDTO createProdutoDTO);

	void delete(Integer id);
}
