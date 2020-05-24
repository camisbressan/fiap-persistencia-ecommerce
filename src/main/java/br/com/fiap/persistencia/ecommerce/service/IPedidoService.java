package br.com.fiap.persistencia.ecommerce.service;

import java.util.List;

import br.com.fiap.persistencia.ecommerce.dto.PedidoDTO;
import br.com.fiap.persistencia.ecommerce.entity.Pedido;

public interface IPedidoService {

	List<PedidoDTO> findAll();
	
	PedidoDTO findById(Integer id);
	
	List<PedidoDTO> findAllByCliente(Integer id);

	Pedido create(Pedido pedido);

	void delete(Integer id);
	
}
