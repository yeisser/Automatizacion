package pe.com.tatiendo.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idPedido;
	private Integer idCliente;
	private Date fecha;
	private Double total;
	private Boolean atendido;
	private Cliente cliente;
	private List<DetallePedido> detallePedido;
	
	public Pedido() {
		this.detallePedido = new ArrayList<DetallePedido>();
		this.cliente = new Cliente();
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		if(detallePedido!=null){
			total = 0D;
			for(DetallePedido detalle : this.detallePedido){
				total += detalle.getSubTotal();
			}
		}
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getAtendido() {
		return atendido;
	}

	public void setAtendido(Boolean atendido) {
		this.atendido = atendido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}
	
	
}
