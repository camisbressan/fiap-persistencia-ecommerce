package br.com.fiap.persistencia.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import br.com.fiap.persistencia.ecommerce.dto.CreateProdutoDTO;
import br.com.fiap.persistencia.ecommerce.dto.ProdutoDTO;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "preco")
	private BigDecimal preco;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<ItensCarrinho> itens;

	public Produto() {
	}
	
	public Produto(String nome, String descricao, Integer quantidade, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Produto(ProdutoDTO produtoDTO) {
		this.id = produtoDTO.getId();
		this.nome = produtoDTO.getNome();
		this.descricao = produtoDTO.getDescricao();
		this.quantidade = produtoDTO.getQuantidade();
		this.preco = produtoDTO.getPreco();
	}
	
	public Produto(CreateProdutoDTO createProdutoDTO) {
		this.nome = createProdutoDTO.getNome();
		this.descricao = createProdutoDTO.getDescricao();
		this.quantidade = createProdutoDTO.getQuantidade();
		this.preco = createProdutoDTO.getPreco();
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

	public Set<ItensCarrinho> getItens() {
		return itens;
	}

	public void setItens(Set<ItensCarrinho> itens) {
		this.itens = itens;
	}

}
