package br.com.fiap.persistencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.persistencia.ecommerce.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

//	@Query("select p from Pedido p where p.cliente.id = :clienteId")
//	public List<Pedido> findPedidoByClienteId(@Param("clienteId") Integer clienteId);
	
	@Query("select p from Pedido p where p.idCliente = :clienteId")
	public List<Pedido> findPedidoByClienteId(@Param("clienteId") Integer clienteId);
}
