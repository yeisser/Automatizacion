package pe.com.tatiendo.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import pe.com.tatiendo.core.dao.ClienteDao;
import pe.com.tatiendo.core.entity.Cliente;
import pe.com.tatiendo.web.util.WebUtil;


@ManagedBean
@ViewScoped
public class IniciarSesionController implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(IniciarSesionController.class);
	private Cliente cliente = new Cliente();
	private ClienteDao clienteDao = new ClienteDao();
	
	public IniciarSesionController() {
		
	}
	
	public String iniciarSesion(){
		String ruta = "";
		try {
			Cliente usuarioInicio = clienteDao.obtener(cliente.getCorreo(), cliente.getClave());
			if(usuarioInicio!=null){
				WebUtil.agregarObjetoSesion("usuarioInicio", usuarioInicio);
				switch (usuarioInicio.getTipoCliente()) {
					case 1:
						//Usuario administrador
						ruta = "bienvenidaAdministrador?faces-redirect=true"; 
						break;
	
					case 2:
						//Usuario jefe de almacen
						ruta = "bienvenidaAlmacen?faces-redirect=true"; 
						break;
						
					case 3:
						//Usuario cliente
						ruta = "bienvenidaCliente?faces-redirect=true";
						break;
				}
			}else{
				WebUtil.mensajeAdvertencia(
						WebUtil.obtenerPropiedad("index.claveIncorrecta"), 
						WebUtil.obtenerPropiedad("index.claveIncorrecta"));
				
			}
		} catch (Exception exception) {
			String mensaje = WebUtil.controlarError(exception, LOGGER);
			WebUtil.mensajeError(mensaje, mensaje);
		}
		return ruta;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
