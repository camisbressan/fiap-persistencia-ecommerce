package br.com.fiap.persistencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("select e from Cliente e where e.email = :clienteEmail and e.senha = :clienteSenha")
	public Cliente findClienteByLogin(@Param("clienteEmail") String clienteEmail, @Param("clienteSenha") String clienteSenha);
	
}
