package br.com.fiap.persistencia.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateEnderecoDTO {

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String logradouro;

	@NotNull
	@Size(min = 1, max = 20, message = "Obrigatório no minimo 1 caracter e no maximo 20.")
	private String numero;

	@Size(max = 45, message = "Limite máximo de 45 caracteres.")
	private String complemento;

	@Size(max = 100, message = "Limite máximo de 100 caracteres.")
	private String bairro;

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 caracteres e no maximo 100.")
	private String cidade;

	@NotNull
	@Size(min = 2, max = 50, message = "Obrigatório no minimo 2 caracteres e no maximo 50.")
	private String estado;

	@NotNull
	@Size(min = 8, max = 8, message = "Obrigatório no minimo 8 dígitos.")
	private String cep;

	private ClienteDTO cliente;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

}
