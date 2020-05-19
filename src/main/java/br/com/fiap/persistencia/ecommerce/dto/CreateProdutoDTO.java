package br.com.fiap.persistencia.ecommerce.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateProdutoDTO {

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String nome;

	@Size(max = 45, message = "Limite máximo de 45 caracteres.")
	private String descricao;

	@Size(max = 11, message = "Limite máximo de 11 dígitos.")
	private Integer quantidade;

	private BigDecimal preco;

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
