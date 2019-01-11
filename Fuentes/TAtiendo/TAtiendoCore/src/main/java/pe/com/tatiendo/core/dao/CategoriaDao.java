package pe.com.tatiendo.core.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.com.tatiendo.core.entity.Categoria;

public class CategoriaDao extends Conexion implements BaseDao<Categoria, Integer>{

	public void insertar(Categoria entity) throws Exception {
		String sql = "INSERT INTO categoria(nombre,habilitado)VALUES(?,?)";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setBoolean(i, true);
		super.preparedStatement.executeUpdate();
		super.resultSet = super.preparedStatement.getGeneratedKeys();
		while(super.resultSet.next()){
			entity.setIdCategoria(super.resultSet.getInt(1));
		}
		liberarRecursos();
		
	}

	public void actualizar(Categoria entity) throws Exception {
		String sql ="UPDATE categoria SET nombre = ? WHERE idCategoria = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setInt(i, entity.getIdCategoria());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
	}

	public void eliminar(Categoria entity) throws Exception {
		String sql ="UPDATE categoria SET habilitado = 0 WHERE idCategoria = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(i, entity.getIdCategoria());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
	}

	public Categoria obtener(Integer id) throws Exception {
		Categoria categoria = null;
		String sql = "SELECT idCategoria, nombre, habilitado FROM categoria WHERE habilitado = 1 and idCategoria = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(i, id);
		super.resultSet = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			categoria = new Categoria();
			categoria.setIdCategoria(super.resultSet.getInt(i));i++;
			categoria.setNombre(super.resultSet.getString(i));i++;
			categoria.setHabilitado(super.resultSet.getBoolean(i));
		}
		liberarRecursos();
		return categoria;
	}

	public List<Categoria> listar(String filtro) throws Exception {
		Categoria categoria = null;
		List<Categoria> lista = new  ArrayList<Categoria>();
		String sql = "SELECT idCategoria, nombre, habilitado FROM categoria WHERE habilitado = 1 AND nombre like concat(?,'%')  ORDER BY nombre";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, filtro.trim().toUpperCase());
		super.resultSet = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			i = 1;
			categoria = new Categoria();
			categoria.setIdCategoria(super.resultSet.getInt(i)); i++;
			categoria.setNombre(super.resultSet.getString(i)); i++;
			categoria.setHabilitado(super.resultSet.getBoolean(i));
			lista.add(categoria);
		}
		liberarRecursos();
		return lista;
	}

}
