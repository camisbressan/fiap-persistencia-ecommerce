package br.com.fiap.persistencia.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.fiap.persistencia.ecommerce.dto.ClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.CreateClienteDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1999741452929037399L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "ddd")
	private Integer ddd;

	@Column(name = "telefone")
	private Long telefone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	//@JsonManagedReference
	private List<Pedido> pedidos;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Endereco> enderecos;

	public Cliente() {
	}

	public Cliente(CreateClienteDTO createClienteDTO) {
		this.nome = createClienteDTO.getNome();
		this.email = createClienteDTO.getEmail();
		this.ddd = createClienteDTO.getDdd();
		this.telefone = createClienteDTO.getTelefone();
	}
	
	public Cliente(ClienteDTO clienteDTO) {
		this.id = clienteDTO.getId();
		this.nome = clienteDTO.getNome();
		this.email = clienteDTO.getEmail();
		this.ddd = clienteDTO.getDdd();
		this.telefone = clienteDTO.getTelefone();
		this.enderecos = converterEnderecos(clienteDTO.getEnderecos());
	}
	
	
	private List<Endereco> converterEnderecos(List<EnderecoDTO> listEndereco){
		if(listEndereco.size() > 0) {
			List<Endereco> listaEnd = new ArrayList<Endereco>();
			for (EnderecoDTO endereco : listEndereco) {
				Endereco dto = new Endereco(endereco);
				listaEnd.add(dto);
			}
			return listaEnd;
		}else {
			return new ArrayList<Endereco>();
		}

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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
