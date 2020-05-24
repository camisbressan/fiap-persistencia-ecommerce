package br.com.fiap.persistencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreatePedidoDTO {

	private Integer idCliente;
	
	private BigDecimal valorTotal;

	private List<ItensCarrinhoDTO> itens = new ArrayList<ItensCarrinhoDTO>();

	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItensCarrinhoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItensCarrinhoDTO> itens) {
		this.itens = itens;
	}

}
