package pe.com.tatiendo.core.dao;

import java.util.List;

public interface BaseDao<E, J> {
	
	void insertar(E entity) throws Exception;
	
	void actualizar(E entity) throws Exception;
	
	void eliminar(E entity) throws Exception;
	
	E obtener(J id) throws Exception;
	
	List<E> listar(String filtro) throws Exception;
	
	

}
