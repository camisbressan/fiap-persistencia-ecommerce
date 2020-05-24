package br.com.fiap.persistencia.ecommerce.dto;

import java.io.Serializable;

import br.com.fiap.persistencia.ecommerce.entity.ItensCarrinho;

public class ItensCarrinhoDTO implements Serializable {

	private static final long serialVersionUID = 4583523775627743344L;

	private ProdutoDTO produto;

	private Integer quantidade;

	public ItensCarrinhoDTO(ProdutoDTO produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public ItensCarrinhoDTO(ItensCarrinho itens) {
		this.produto = new ProdutoDTO(itens.getProduto());
		this.quantidade = itens.getQuantidade();
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
