package br.com.fiap.persistencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.persistencia.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
