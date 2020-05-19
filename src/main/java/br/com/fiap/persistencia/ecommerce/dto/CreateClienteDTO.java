package br.com.fiap.persistencia.ecommerce.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateClienteDTO {

	@NotNull
	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 letras e no maximo 100.")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Size(min = 5, max = 100, message = "Obrigatório no minimo 5 letras e no maximo 100.")
	@Column(name = "email")
	private String email;

	@Column(name = "ddd", length = 2, nullable = true)
	private Integer ddd;

	@Column(name = "telefone", length = 9, nullable = true)
	private Long telefone;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
//	@JsonManagedReference
//	private Set<Pedido> pedidos = new LinkedHashSet<Pedido>();

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
