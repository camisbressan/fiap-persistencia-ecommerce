package br.com.fiap.persistencia.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public class EnderecoDTO {

	private Integer id;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private ClienteDTO cliente;

	public EnderecoDTO() {
	}

	public EnderecoDTO(Integer id, String logradouro, String numero, String complemento, String bairro, String cidade,
			String estado, String cep, ClienteDTO cliente) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.cliente = cliente;
	}

	public EnderecoDTO(CreateEnderecoDTO enderecoDTO, Integer id) {
		this.id = id;
		this.logradouro = enderecoDTO.getLogradouro();
		this.numero = enderecoDTO.getNumero();
		this.complemento = enderecoDTO.getComplemento();
		this.bairro = enderecoDTO.getBairro();
		this.cidade = enderecoDTO.getCidade();
		this.estado = enderecoDTO.getEstado();
		this.cep = enderecoDTO.getCep();
		this.cliente = enderecoDTO.getCliente();
	}

	public EnderecoDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.cep = endereco.getCep();
		this.cliente = new ClienteDTO(endereco.getCliente());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
