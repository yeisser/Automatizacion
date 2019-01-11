package pe.com.tatiendo.core.dao;

import java.io.Serializable;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.com.tatiendo.core.entity.Cliente;

public class ClienteDao extends Conexion implements BaseDao<Cliente, Integer>, Serializable {


	private static final long serialVersionUID = 1L;

	public void insertar(Cliente entity) throws Exception {
		String sql = "INSERT INTO cliente(nombre,apellido,correo,clave,tipoCliente,habilitado)VALUES(?,?,?,?,?,?)";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getApellido().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getCorreo().trim()); i++;
		super.preparedStatement.setString(i, entity.getClave()); i++;
		super.preparedStatement.setInt(i, entity.getTipoCliente()); i++;
		super.preparedStatement.setBoolean(i, true);
		super.preparedStatement.executeUpdate();
		super.resultSet = super.preparedStatement.getGeneratedKeys();
		while(super.resultSet.next()){
			entity.setIdCliente(super.resultSet.getInt(1));
		}
		liberarRecursos();
	}

	public void actualizar(Cliente entity) throws Exception {
		String sql = "UPDATE cliente SET nombre = ?, apellido = ?, correo = ?, clave = ?, tipoCliente = ? WHERE idCliente = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getApellido().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getCorreo().trim()); i++;
		super.preparedStatement.setString(i, entity.getClave().trim()); i++;
		super.preparedStatement.setInt(i, entity.getTipoCliente()); i++;
		super.preparedStatement.setInt(i, entity.getIdCliente());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
	}

	public void eliminar(Cliente entity) throws Exception {
		String sql = "UPDATE cliente SET habilitado = 0 WHERE idCliente = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(i, entity.getIdCliente());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
	}

	public Cliente obtener(Integer id) throws Exception {
		Cliente cliente = null;
		String sql = "SELECT idCliente, nombre, apellido, correo, clave, tipoCliente, habilitado FROM cliente WHERE habilitado=1 and idCliente=? ORDER BY apellido";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(i, id);
		super.resultSet = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			cliente = new Cliente();
			cliente.setIdCliente(super.resultSet.getInt(i)); i++;
			cliente.setNombre(super.resultSet.getString(i)); i++;
			cliente.setApellido(super.resultSet.getString(i)); i++;
			cliente.setCorreo(super.resultSet.getString(i)); i++;
			cliente.setClave(super.resultSet.getString(i)); i++;
			cliente.setTipoCliente(super.resultSet.getInt(i)); i++;
			cliente.setHabilitado(super.resultSet.getBoolean(i));
		}
		liberarRecursos();
		return cliente;
	}
	
	public Cliente obtener(String correo, String clave) throws Exception {
		Cliente cliente = null;
		String sql = "SELECT idCliente, nombre, apellido, correo, clave, tipoCliente, habilitado FROM cliente WHERE habilitado=1 and correo = ? and clave = ? ORDER BY apellido";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, correo); i++;
		super.preparedStatement.setString(i, clave);
		super.resultSet = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			i = 1;
			cliente = new Cliente();
			cliente.setIdCliente(super.resultSet.getInt(i)); i++;
			cliente.setNombre(super.resultSet.getString(i)); i++;
			cliente.setApellido(super.resultSet.getString(i)); i++;
			cliente.setCorreo(super.resultSet.getString(i)); i++;
			cliente.setClave(super.resultSet.getString(i)); i++;
			cliente.setTipoCliente(super.resultSet.getInt(i)); i++;
			cliente.setHabilitado(super.resultSet.getBoolean(i));
			
		}
		liberarRecursos();
		return cliente;
	}

	public List<Cliente> listar(String filtro) throws Exception {
		Cliente cliente = null;
		List<Cliente> lista = new ArrayList<Cliente>();
		String sql = "SELECT idCliente, nombre, apellido, correo, clave, tipoCliente, habilitado FROM cliente WHERE habilitado=1 and apellido like concat(?, '%') ORDER BY apellido";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, filtro.trim().toUpperCase());
		super.resultSet = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			i = 1;
			cliente = new Cliente();
			cliente.setIdCliente(super.resultSet.getInt(i)); i++;
			cliente.setNombre(super.resultSet.getString(i)); i++;
			cliente.setApellido(super.resultSet.getString(i)); i++;
			cliente.setCorreo(super.resultSet.getString(i)); i++;
			cliente.setClave(super.resultSet.getString(i)); i++;
			cliente.setTipoCliente(super.resultSet.getInt(i)); i++;
			cliente.setHabilitado(super.resultSet.getBoolean(i));
			lista.add(cliente);
		}
		liberarRecursos();
		return lista;
	}

}
