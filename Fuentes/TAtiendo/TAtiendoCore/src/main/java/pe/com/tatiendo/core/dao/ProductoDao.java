package pe.com.tatiendo.core.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.com.tatiendo.core.entity.Producto;

public class ProductoDao extends Conexion implements BaseDao<Producto, Integer> {

	public void insertar(Producto entity) throws Exception {
		String sql = "INSERT INTO producto(nombre,descripcion,idCategoria,esInterno,ubicacionProducto,precio,cantidad,habilitado)VALUES(?,?,?,?,?,?,?,?)";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getDescripcion().trim().toUpperCase()); i++;
		super.preparedStatement.setInt(i, entity.getIdCategoria()); i++;
		super.preparedStatement.setBoolean(i, entity.getEsInterno()); i++;
		super.preparedStatement.setInt(i, entity.getUbicacionProducto()); i++;
		super.preparedStatement.setDouble(i, entity.getPrecio()); i++;
		super.preparedStatement.setFloat(i, entity.getCantidad()); i++;
		super.preparedStatement.setBoolean(i, true);
		super.preparedStatement.executeUpdate();
		super.resultSet = super.preparedStatement.getGeneratedKeys();
		while(super.resultSet.next()){
			entity.setIdProducto(super.resultSet.getInt(1));
		}
		liberarRecursos();
	}

	public void actualizar(Producto entity) throws Exception {
		String sql = "UPDATE producto SET nombre = ?, descripcion = ?, idCategoria = ?, esInterno = ?, ubicacionProducto = ?, precio = ?, cantidad = ? WHERE idProducto = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, entity.getNombre().trim().toUpperCase()); i++;
		super.preparedStatement.setString(i, entity.getDescripcion().trim().toUpperCase()); i++;
		super.preparedStatement.setInt(i, entity.getIdCategoria()); i++;
		super.preparedStatement.setBoolean(i, entity.getEsInterno()); i++;
		super.preparedStatement.setInt(i, entity.getUbicacionProducto()); i++;
		super.preparedStatement.setDouble(i, entity.getPrecio()); i++;
		super.preparedStatement.setFloat(i, entity.getCantidad()); i++;
		super.preparedStatement.setInt(i, entity.getIdProducto());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
	}

	public void eliminar(Producto entity) throws Exception {
		String sql ="UPDATE producto SET habilitado = 0 WHERE idProducto = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(i, entity.getIdProducto());
		super.preparedStatement.executeUpdate();
		liberarRecursos();
		
	}

	public Producto obtener(Integer id) throws Exception {
		Producto producto = null;
		String sql = "SELECT idProducto, nombre, descripcion, idCategoria, esInterno, ubicacionProducto, precio, cantidad, habilitado FROM Producto WHERE idProducto = ?";
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setInt(1, id);
		super.resultSet  = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			producto = new Producto();
			producto.setIdProducto(super.resultSet.getInt(i)); i++;
			producto.setNombre(super.resultSet.getString(i)); i++;
			producto.setDescripcion(super.resultSet.getString(i)); i++;
			producto.setIdCategoria(super.resultSet.getInt(i)); i++;
			producto.setEsInterno(super.resultSet.getBoolean(i)); i++;
			producto.setUbicacionProducto(super.resultSet.getInt(i)); i++;
			producto.setPrecio(super.resultSet.getDouble(i)); i++;
			producto.setCantidad(super.resultSet.getFloat(i)); i++;
			producto.setHabilitado(super.resultSet.getBoolean(i));
		}
		liberarRecursos();
		return producto;
	}

	public List<Producto> listar(String filtro) throws Exception {
		String sql = "SELECT idProducto, nombre, descripcion, idCategoria, esInterno, ubicacionProducto, precio, cantidad, habilitado FROM producto WHERE habilitado = 1 AND nombre like concat(?,'%')  ORDER BY nombre";
		Producto producto = null;
		List<Producto> lista = new ArrayList<Producto>();
		int i = 1;
		obtenerConexion();
		super.preparedStatement = super.connection.prepareStatement(sql);
		super.preparedStatement.setString(i, filtro.trim().toUpperCase());
		super.resultSet  = super.preparedStatement.executeQuery();
		while(super.resultSet.next()){
			i = 1;
			producto = new Producto();
			producto.setIdProducto(super.resultSet.getInt(i)); i++;
			producto.setNombre(super.resultSet.getString(i)); i++;
			producto.setDescripcion(super.resultSet.getString(i)); i++;
			producto.setIdCategoria(super.resultSet.getInt(i)); i++;
			producto.setEsInterno(super.resultSet.getBoolean(i)); i++;
			producto.setUbicacionProducto(super.resultSet.getInt(i)); i++;
			producto.setPrecio(super.resultSet.getDouble(i)); i++;
			producto.setCantidad(super.resultSet.getFloat(i)); i++;
			producto.setHabilitado(super.resultSet.getBoolean(i));
			lista.add(producto);
		}
		liberarRecursos();
		return lista;
	}

}
