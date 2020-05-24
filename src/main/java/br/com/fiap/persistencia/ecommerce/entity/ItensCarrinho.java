package br.com.fiap.persistencia.ecommerce.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.fiap.persistencia.ecommerce.dto.ProdutoDTO;

@Entity
public class ItensCarrinho implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn
	private Produto produto;

	@Id
	@ManyToOne
	@JoinColumn
	private Pedido pedido;

	private Integer quantidade;

	public ItensCarrinho(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public ItensCarrinho(ProdutoDTO produtoDTO, Integer quantidade) {
		this.produto = new Produto(produtoDTO);
		this.quantidade = quantidade;
	}
	public ItensCarrinho() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ItensCarrinho))
			return false;
		ItensCarrinho that = (ItensCarrinho) o;
		return Objects.equals(produto.getId(), that.produto.getId())
				&& Objects.equals(pedido.getId(), that.pedido.getId()) && quantidade == that.quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto.getId(), pedido.getId(), quantidade);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
