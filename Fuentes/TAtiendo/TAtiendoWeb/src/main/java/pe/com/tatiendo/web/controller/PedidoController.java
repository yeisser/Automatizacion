package pe.com.tatiendo.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import pe.com.tatiendo.core.dao.PedidoDao;
import pe.com.tatiendo.core.dao.ProductoDao;
import pe.com.tatiendo.core.entity.Cliente;
import pe.com.tatiendo.core.entity.DetallePedido;
import pe.com.tatiendo.core.entity.Pedido;
import pe.com.tatiendo.core.entity.Producto;
import pe.com.tatiendo.web.util.WebUtil;

@ManagedBean
@SessionScoped
public class PedidoController implements Serializable{

	private static final Logger LOGGER = Logger.getLogger(CategoriaController.class);
	private static final long serialVersionUID = 1L;
	private List<SelectItem> listaItemProductos = null;
	private List<DetallePedido> listaDetallePedidos = new ArrayList<DetallePedido>();
	private Pedido pedido = new Pedido();
	private DetallePedido detallePedido = new DetallePedido();
	private DetallePedido detallePedidoSeleccionado = new DetallePedido();
	private Producto productoSeleccionado = new Producto();
	private String inicioMantenimiento = "";
	private ProductoDao productoDao = new ProductoDao();
	private PedidoDao pedidoDao = new PedidoDao();
	
	public PedidoController() {
		
	}
	
	private void inicializarProductos(){
		try {
			this.listaItemProductos = new ArrayList<SelectItem>();
			List<Producto> listaProductos = productoDao.listar("");
			this.productoSeleccionado = listaProductos.get(0);
			for(Producto producto : listaProductos){
				this.listaItemProductos.add(new SelectItem(producto.getIdProducto(), producto.getNombre()));
			}
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}
	}
	public void cambiarItem(){
		try {
			this.productoSeleccionado = productoDao.obtener(this.productoSeleccionado.getIdProducto());
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}

	}
	
	public void agregar(){
		try{
			boolean esNuevo = true;
			for(int i = 0; i< this.listaDetallePedidos.size(); i++){
				DetallePedido detTemp = this.listaDetallePedidos.get(i);
				if(detTemp.getIdProducto() == this.productoSeleccionado.getIdProducto()){
					esNuevo=false;
					detTemp.setCantidad(this.detallePedido.getCantidad());
					this.listaDetallePedidos.set(i, detTemp);
					this.pedido.setDetallePedido(this.listaDetallePedidos);
					this.detallePedido = new DetallePedido();
					break;
				}
			}
			if(esNuevo){
				this.detallePedido.setProducto(this.productoDao.obtener(this.productoSeleccionado.getIdProducto()));
				this.detallePedido.setIdProducto(this.productoSeleccionado.getIdProducto());
				this.listaDetallePedidos.add(detallePedido);
				this.pedido.setDetallePedido(this.listaDetallePedidos);
				this.detallePedido = new DetallePedido();
			}
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}
	}
	
	public void guardar(){
		try {
			Cliente cliente = (Cliente)WebUtil.obtenerObjetoSesion("usuarioInicio");
			this.pedido.setFecha(new Date());
			this.pedido.setCliente(cliente);
			this.pedido.setIdCliente(cliente.getIdCliente());
			this.pedidoDao.insertar(pedido);
			WebUtil.mensajeInformacion(
					WebUtil.obtenerPropiedad("pedidoController.guardarExito", this.pedido.getIdPedido()), 
					WebUtil.obtenerPropiedad("pedidoController.guardarExito", this.pedido.getIdPedido()));
			this.listaDetallePedidos = new ArrayList<DetallePedido>();
			this.pedido = new Pedido();
			this.detallePedido = new DetallePedido();
			this.inicializarProductos();
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}
	}
	
	public void quitar(){
		try{
			if(detallePedidoSeleccionado!=null){
				for(int i = 0; i< this.listaDetallePedidos.size(); i++){
					DetallePedido detTemp = this.listaDetallePedidos.get(i);
					if(detTemp.getIdProducto() == this.detallePedidoSeleccionado.getIdProducto()){
						this.listaDetallePedidos.remove(i);
						this.pedido.setDetallePedido(this.listaDetallePedidos);
						break;
					}
				}
			}
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}
	}
	
	public String getInicioMantenimiento() {
		this.inicializarProductos();
		return inicioMantenimiento;
	}

	public void setInicioMantenimiento(String inicioMantenimiento) {
		this.inicioMantenimiento = inicioMantenimiento;
	}

	public List<SelectItem> getListaItemProductos() {
		return listaItemProductos;
	}

	public void setListaItemProductos(List<SelectItem> listaItemProductos) {
		this.listaItemProductos = listaItemProductos;
	}

	public List<DetallePedido> getListaDetallePedidos() {
		return listaDetallePedidos;
	}

	public void setListaDetallePedidos(List<DetallePedido> listaDetallePedidos) {
		this.listaDetallePedidos = listaDetallePedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public DetallePedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}
	
	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	
	public DetallePedido getDetallePedidoSeleccionado() {
		return detallePedidoSeleccionado;
	}
	
	public void setDetallePedidoSeleccionado(
			DetallePedido detallePedidoSeleccionado) {
		this.detallePedidoSeleccionado = detallePedidoSeleccionado;
	}

}
