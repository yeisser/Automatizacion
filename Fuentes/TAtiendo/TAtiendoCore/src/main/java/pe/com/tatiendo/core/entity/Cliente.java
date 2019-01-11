package pe.com.tatiendo.core.entity;

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private String correo;
	private String clave;
	private Integer tipoCliente;
	private String descripcionTipoCliente;
	private Boolean habilitado;

	public Cliente() {

	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getDescripcionTipoCliente() {
		switch (this.tipoCliente) {
		case 1:
			this.descripcionTipoCliente = "Administrador";
			break;
		case 2:
			this.descripcionTipoCliente = "Jefe Almacen";
			break;
		case 3:
			this.descripcionTipoCliente = "Cliente";
			break;
		}
		return descripcionTipoCliente;
	}

	public void setDescripcionTipoCliente(String descripcionTipoCliente) {
		this.descripcionTipoCliente = descripcionTipoCliente;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

}
