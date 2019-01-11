package pe.com.tatiendo.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import pe.com.tatiendo.core.dao.ClienteDao;
import pe.com.tatiendo.core.entity.Cliente;
import pe.com.tatiendo.web.util.WebUtil;

@ManagedBean
@ViewScoped
public class UsuarioExternoController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(IniciarSesionController.class);
	private Cliente cliente = new Cliente();
	private ClienteDao clienteDao = new ClienteDao();
	
	public UsuarioExternoController() {
	
	}
	
	public void guardar(){
		try{
			this.cliente.setTipoCliente(3);
			this.clienteDao.insertar(this.cliente);
			this.cliente = new Cliente();
			WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("registrarUsuario.guardarExito"), WebUtil.obtenerPropiedad("registrarUsuario.guardarExito"));
		}catch(Exception exception){
			if(exception instanceof MySQLIntegrityConstraintViolationException){
				WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("registrarUsuario.correoExiste"), WebUtil.obtenerPropiedad("registrarUsuario.correoExiste"));
			}else{
				String mensaje = WebUtil.controlarError(exception, LOGGER);
				WebUtil.mensajeError(mensaje, mensaje);
			}
		}
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
