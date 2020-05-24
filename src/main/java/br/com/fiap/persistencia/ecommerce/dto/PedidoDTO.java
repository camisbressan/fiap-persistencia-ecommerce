package br.com.fiap.persistencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import br.com.fiap.persistencia.ecommerce.entity.ItensCarrinho;
import br.com.fiap.persistencia.ecommerce.entity.Pedido;

public class PedidoDTO {

	private Integer id;

	private Integer idCliente;

	private BigDecimal valorTotal;

	private Date dataPedido;

	private List<ItensCarrinhoDTO> itens = new ArrayList<ItensCarrinhoDTO>();

	public PedidoDTO() {
	}

	public PedidoDTO(Integer id, Integer idCliente, BigDecimal valorTotal, List<ItensCarrinhoDTO> itens) {
		this.id = id;
		this.idCliente = idCliente;
		this.valorTotal = valorTotal;
		this.itens = itens;
	}

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.idCliente = pedido.getIdCliente();
		this.valorTotal = pedido.getValorTotal();
		this.dataPedido = pedido.getDataPedido();
		this.itens = converterItens(pedido.getItens());
	}

	private List<ItensCarrinhoDTO> converterItens(Set<ItensCarrinho> itens) {
		List<ItensCarrinhoDTO> lista = new ArrayList<ItensCarrinhoDTO>();
		for (ItensCarrinho itensCarrinho : itens) {
			lista.add(new ItensCarrinhoDTO(itensCarrinho));
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

}
