package br.com.fiap.persistencia.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public class ClienteDTO {

	private Integer id;

	private String nome;

	private String email;

	private String senha;

	private Integer ddd;

	private Long telefone;

	private List<EnderecoDTO> enderecos;

	public ClienteDTO() {
	}

	public ClienteDTO(Integer id, String nome, String email, Integer ddd, Long telefone, List<EnderecoDTO> enderecos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.ddd = ddd;
		this.telefone = telefone;
		this.enderecos = enderecos;
	}

	public ClienteDTO(CreateClienteDTO createClienteDTO, Integer id) {
		this.id = id;
		this.nome = createClienteDTO.getNome();
		this.email = createClienteDTO.getEmail();
		this.senha = createClienteDTO.getSenha();
		this.ddd = createClienteDTO.getDdd();
		this.telefone = createClienteDTO.getTelefone();
		this.enderecos = createClienteDTO.getEnderecos();
	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.ddd = cliente.getDdd();
		this.telefone = cliente.getTelefone();
		this.enderecos = converterEnderecos(cliente.getEnderecos());
		// this.pedidos = cliente.getPedidos();
	}

	private List<EnderecoDTO> converterEnderecos(List<Endereco> setEndereco) {
		if (setEndereco.size() > 0) {
			List<EnderecoDTO> listaEnd = new ArrayList<EnderecoDTO>();
			for (Endereco endereco : setEndereco) {
				EnderecoDTO dto = new EnderecoDTO();
				dto.setId(endereco.getId());
				dto.setLogradouro(endereco.getLogradouro());
				dto.setNumero(endereco.getNumero());
				dto.setComplemento(endereco.getComplemento());
				dto.setBairro(endereco.getBairro());
				dto.setCidade(endereco.getCidade());
				dto.setEstado(endereco.getEstado());
				dto.setCep(endereco.getCep());
				listaEnd.add(dto);
			}
			return listaEnd;
		} else {
			return new ArrayList<EnderecoDTO>();
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
