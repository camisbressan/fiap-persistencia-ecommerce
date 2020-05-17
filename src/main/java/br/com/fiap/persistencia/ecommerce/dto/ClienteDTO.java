package br.com.fiap.persistencia.ecommerce.dto;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;

public class ClienteDTO {

	private Integer id;

	private String nome;

	private String email;

	private Integer telefone;

	private List<EnderecoDTO> enderecos;

	public ClienteDTO() {}
	
	public ClienteDTO(Integer id, String nome, String email, Integer telefone, List<EnderecoDTO> enderecos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.enderecos = enderecos;
	}
	
	public ClienteDTO(CreateClienteDTO clienteDTO, Integer id) {
		this.id = id;
		this.nome = clienteDTO.getNome();
		this.email = clienteDTO.getEmail();
		this.telefone = clienteDTO.getTelefone();
		this.enderecos = clienteDTO.getEnderecos();
	}

	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		//this.enderecos = cliente.getEnderecos();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
	
}
