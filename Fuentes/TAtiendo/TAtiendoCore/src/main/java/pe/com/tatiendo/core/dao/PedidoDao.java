package pe.com.tatiendo.core.dao;

import java.sql.Statement;
import java.util.List;

import pe.com.tatiendo.core.entity.DetallePedido;
import pe.com.tatiendo.core.entity.Pedido;

public class PedidoDao extends Conexion implements BaseDao<Pedido, Integer>{

	public void insertar(Pedido entity) throws Exception {
		String sql = "INSERT INTO pedido(idCliente,fecha,total,atendido)VALUES(?,?,?,?)";
		int i = 1;
		obtenerConexion();
		super.connection.setAutoCommit(false);
		super.preparedStatement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		super.preparedStatement.setInt(i, entity.getIdCliente()); i++;
		super.preparedStatement.setDate(i, new java.sql.Date(entity.getFecha().getTime())); i++;
		super.preparedStatement.setDouble(i, entity.getTotal()); i++;
		super.preparedStatement.setBoolean(i, false);i++;
		super.preparedStatement.executeUpdate();
		resultSet = super.preparedStatement.getGeneratedKeys();
		while(resultSet.next()){
			entity.setIdPedido(resultSet.getInt(1));
		}
		List<DetallePedido> lista = entity.getDetallePedido();
		sql = "INSERT INTO detallepedido(idPedido,idProducto,cantidad,subTotal)VALUES(?,?,?,?)";
		for(DetallePedido detalle : lista ){
			i=1;
			super.preparedStatement = super.connection.prepareStatement(sql);
			super.preparedStatement.setInt(i, entity.getIdPedido()); i++;
			super.preparedStatement.setInt(i, detalle.getIdProducto()); i++;
			super.preparedStatement.setDouble(i, detalle.getCantidad()); i++;
			super.preparedStatement.setDouble(i, detalle.getSubTotal());
			super.preparedStatement.executeUpdate();
		}
		super.connection.commit();
		liberarRecursos();
	}

	public void actualizar(Pedido entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void eliminar(Pedido entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Pedido obtener(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> listar(String filtro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
