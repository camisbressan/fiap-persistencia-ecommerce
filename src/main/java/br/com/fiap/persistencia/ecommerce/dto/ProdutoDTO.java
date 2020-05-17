package br.com.fiap.persistencia.ecommerce.dto;

import java.math.BigDecimal;

import br.com.fiap.persistencia.ecommerce.entity.Produto;

public class ProdutoDTO {

	private Integer id;

	private String nome;

	private String descricao;

	private Integer quantidade;

	private BigDecimal preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Integer id, String nome, String descricao, Integer quantidade, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public ProdutoDTO(CreateProdutoDTO produtoDTO, Integer id) {
		this.id = id;
		this.nome = produtoDTO.getNome();
		this.descricao = produtoDTO.getDescricao();
		this.quantidade = produtoDTO.getQuantidade();
		this.preco = produtoDTO.getPreco();
	}

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidade = produto.getQuantidade();
		this.preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
