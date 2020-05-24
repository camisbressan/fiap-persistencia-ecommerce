package br.com.fiap.persistencia.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 6650254311018170412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_cliente", nullable = false)
//	@JsonBackReference
//	private Cliente cliente;

	@Column(name = "id_cliente")
	private Integer idCliente;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "data_compra")
	private Date dataPedido;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Set<ItensCarrinho> itens = new HashSet<>();

	public Pedido() {
	}

	public Pedido(Integer idCliente, BigDecimal valorTotal, Date dataPedido, ItensCarrinho... carrinho) {
		this.idCliente = idCliente;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
		for (ItensCarrinho item : carrinho)
			item.setPedido(this);
		this.itens = Stream.of(carrinho).collect(Collectors.toSet());
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

	public Set<ItensCarrinho> getItens() {
		return itens;
	}

	public void setItens(Set<ItensCarrinho> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

}
