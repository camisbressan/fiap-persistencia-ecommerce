package br.com.fiap.persistencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.persistencia.ecommerce.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	@Query("select e from Endereco e where e.cliente.id = :clienteId")
	public List<Endereco> findAllByClienteId(@Param("clienteId") Integer clienteId);
	
	@Query("select e from Endereco e where e.cliente.id = :clienteId and e.id = :endId")
	public Endereco findEnderecoByClienteId(@Param("clienteId") Integer clienteId, @Param("endId") Integer endId);
}