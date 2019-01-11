package pe.com.tatiendo.core.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import static pe.com.tatiendo.core.util.CoreUtil.obtenerPropiedad;

public class Conexion {

	protected Connection connection;
	protected ResultSet resultSet;
	protected PreparedStatement preparedStatement;
	
	public Conexion() {
		
	}
	
	public void obtenerConexion() throws Exception{
		Class.forName(obtenerPropiedad("conexion.class"));
		this.connection = DriverManager.getConnection(obtenerPropiedad("conexion.url"), obtenerPropiedad("conexion.usuario"), obtenerPropiedad("conexion.clave"));
	}
	
	public void liberarRecursos(){
		try {
			if(this.resultSet!=null && ! this.resultSet.isClosed()){
				this.resultSet.close();
			}
			if(this.preparedStatement!=null && !this.preparedStatement.isClosed()){
				this.preparedStatement.close();
			}
			if(this.connection!=null && !this.connection.isClosed()){
				this.connection.close();
			}
		} catch (Exception e) {
			
		}
	}
}
