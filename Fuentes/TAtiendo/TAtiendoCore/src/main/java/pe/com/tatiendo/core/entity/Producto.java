package pe.com.tatiendo.core.entity;

import java.io.Serializable;

public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private String nombre;
	private String descripcion;
	private Integer idCategoria;
	private Boolean esInterno;
	private Integer ubicacionProducto;
	private Double precio;
	private Float cantidad;
	private Boolean habilitado;
	private Categoria categoria;
	
	public Producto() {
		this.categoria = new Categoria();
	}
	
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Boolean getEsInterno() {
		return esInterno;
	}
	public void setEsInterno(Boolean esInterno) {
		this.esInterno = esInterno;
	}
	public Integer getUbicacionProducto() {
		return ubicacionProducto;
	}
	public void setUbicacionProducto(Integer ubicacionProducto) {
		this.ubicacionProducto = ubicacionProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
