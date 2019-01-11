package pe.com.tatiendo.core.entity;

import java.io.Serializable;

public class DetallePedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idPedido;
	private Integer idProducto;
	private Double cantidad;
	private Double subTotal;
	private Pedido pedido;
	private Producto producto;
	
	public DetallePedido() {
		this.pedido = new Pedido();
		this.producto = new Producto();
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubTotal() {
		this.subTotal = this.cantidad * this.producto.getPrecio();
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

}
