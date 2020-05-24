package br.com.fiap.persistencia.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fiap.persistencia.ecommerce.dto.CreateEnderecoDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 425064054437646105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "cep")
	private String cep;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	public Endereco() {}
	
	public Endereco(CreateEnderecoDTO createEnderecoDTO) {
		this.logradouro = createEnderecoDTO.getLogradouro();
		this.numero = createEnderecoDTO.getNumero();
		this.complemento = createEnderecoDTO.getComplemento();
		this.bairro = createEnderecoDTO.getBairro();
		this.cidade = createEnderecoDTO.getCidade();
		this.estado = createEnderecoDTO.getEstado();
		this.cep = createEnderecoDTO.getCep();
		this.cliente = new Cliente(createEnderecoDTO.getCliente());
	}

	public Endereco(EnderecoDTO enderecoDTO) {
		this.id = enderecoDTO.getId();
		this.logradouro = enderecoDTO.getLogradouro();
		this.numero = enderecoDTO.getNumero();
		this.complemento = enderecoDTO.getComplemento();
		this.bairro = enderecoDTO.getBairro();
		this.cidade = enderecoDTO.getCidade();
		this.estado = enderecoDTO.getEstado();
		this.cep = enderecoDTO.getCep();
		this.cliente = new Cliente(enderecoDTO.getCliente());
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}