package br.com.fiap.persistencia.ecommerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreatePedidoDTO implements Serializable {

	private static final long serialVersionUID = -3461901210133976525L;

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
