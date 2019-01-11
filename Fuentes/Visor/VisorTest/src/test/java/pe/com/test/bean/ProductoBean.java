package pe.com.test.bean;

import pe.com.core.entity.Categoria;

public class ProductoBean {

	private String nombre;
	private Double precio;
	private Integer idCategoria;
	private Categoria categoria;

	
	public ProductoBean(String nombre, Double precio, Integer idCategoria) {
		super();
		this.nombre = nombre;
		this.precio=precio;
		this.idCategoria=idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio){
		this.precio=precio;
	}
	
	public Integer getIdCategoria(){
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria){
		this.idCategoria=idCategoria;
	}
	

	public Categoria getCategoria(){
		return categoria;
	}
	public void setCategoria(Categoria categoria){
		this.categoria=categoria;
		}
	
	
	@Override
	public String toString() {
		return "ProductoBean[nombre=" + nombre + ", precio=" + precio +", idCategoria=" + idCategoria + "]";
	
	}
}
