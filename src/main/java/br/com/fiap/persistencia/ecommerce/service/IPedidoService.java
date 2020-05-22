package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.entity.Pedido;

public interface IPedidoService {

	List<Pedido> findAll();
	
	Pedido findById(Integer id);
	
	List<Pedido> findAllByCliente(Integer id);

	Pedido create(Pedido pedido);

	Pedido update(Pedido pedido);

	void delete(Integer id);
	
}
