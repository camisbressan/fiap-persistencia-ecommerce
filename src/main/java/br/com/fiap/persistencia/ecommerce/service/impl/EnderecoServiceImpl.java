package br.com.fiap.persistencia.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.persistencia.ecommerce.dto.CreateEnderecoDTO;
import br.com.fiap.persistencia.ecommerce.dto.EnderecoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Endereco;
import br.com.fiap.persistencia.ecommerce.repository.EnderecoRepository;
import br.com.fiap.persistencia.ecommerce.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public List<EnderecoDTO> findAll() {
		List<Endereco> enderecosList = enderecoRepository.findAll();
		return enderecosList.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}

	@Override
	public EnderecoDTO findById(Integer id) {
		return new EnderecoDTO(
				enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}

	@Override
	public EnderecoDTO create(CreateEnderecoDTO createEnderecoDTO) {
		Endereco endereco = new Endereco(createEnderecoDTO);
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}

	@Override
	public EnderecoDTO update(Integer id, CreateEnderecoDTO createEnderecoDTO) {
		Endereco endereco = new Endereco();
		endereco.setId(id);
		endereco.setLogradouro(createEnderecoDTO.getLogradouro());
		endereco.setNumero(createEnderecoDTO.getNumero());
		endereco.setComplemento(createEnderecoDTO.getComplemento());
		endereco.setBairro(createEnderecoDTO.getBairro());
		endereco.setCidade(createEnderecoDTO.getCidade());
		endereco.setEstado(createEnderecoDTO.getEstado());
		endereco.setCep(createEnderecoDTO.getCep());
		// endereco.setCliente(createEnderecoDTO.getCliente());
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}

	@Override
	public void delete(Integer id) {
		enderecoRepository.deleteById(id);
	}

}
