package br.com.fiap.persistencia.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateClienteDTO implements Serializable {

	private static final long serialVersionUID = 546207927443713436L;

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String nome;

	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String email;

	private String senha;

	private Integer ddd;

	private Long telefone;

	private List<EnderecoDTO> enderecos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

}
