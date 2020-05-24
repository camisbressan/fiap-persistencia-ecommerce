package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.persistencia.ecommerce.dto.CreateProdutoDTO;
import br.com.fiap.persistencia.ecommerce.dto.ProdutoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Produto;
import br.com.fiap.persistencia.ecommerce.repository.ProdutoRepository;
import br.com.fiap.persistencia.ecommerce.service.IProdutoService;

@Service
public class ProdutoServiceImpl implements IProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	@Cacheable(value = "allProdutosCache", unless = "#result.size() == 0")
	public List<ProdutoDTO> findAll() {
		List<Produto> produtosList = produtoRepository.findAll();
		return produtosList.stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "produtoCache", key = "#id")
	public ProdutoDTO findById(Integer id) {
		return new ProdutoDTO(
				produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public ProdutoDTO create(CreateProdutoDTO createProdutoDTO) {
		Produto produto = new Produto(createProdutoDTO);
		return new ProdutoDTO(produtoRepository.save(produto));
	}

	@Override
	public ProdutoDTO update(Integer id, CreateProdutoDTO createProdutoDTO) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(createProdutoDTO.getNome());
		produto.setDescricao(createProdutoDTO.getDescricao());
		produto.setQuantidade(createProdutoDTO.getQuantidade());
		produto.setPreco(createProdutoDTO.getPreco());
		return new ProdutoDTO(produtoRepository.save(produto));
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "produtoCache", key = "#id"),
			@CacheEvict(value = "allProdutosCache", allEntries = true) })
	public void delete(Integer id) {
		produtoRepository.deleteById(id);
	}

}
