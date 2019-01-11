package pe.com.core.test;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pe.com.core.business.ProductoBusiness;
import pe.com.core.entity.Producto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductoTest {

	private final ProductoBusiness productoBusiness = new ProductoBusiness();
	private static Producto producto;
	
	@BeforeClass
	public static void inicioClase() {
		System.out.println("Inicio de la clase");
	}
	
	@AfterClass
	public static void finClase() {
		System.out.println("Fin de la clase");
	}

	@Before
	public void inicioMetodo() {
		System.out.println("Inicio Metodo");
	}

	@After
	public void finMetodo() {
		System.out.println("Fin Metodo");
	}
	
	@Test
	public void a_insertar() {
		try {
			System.out.println("Método Insertar");
			//Creando un nuevo Producto.
			producto =new Producto();
			producto.setNombre("Prueba JUnit");
			producto.setPrecio(15.3);
			
			producto.setIdCategoria(3);
			//Insertando un nuevo Producto.
			productoBusiness.insertar(producto);
			//Asser es un punto de verificación.
			Assert.assertTrue(producto.getIdProducto() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void b_actualizar() {
		try {
			System.out.println("Método Actualiza");
			producto.setNombre("Prueba JUnit actualizada");
			productoBusiness.actualizar(producto);
			Assert.assertTrue(producto.getIdProducto() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	
	public void c_obtener() {
		try {
			System.out.println("Método Obtener");
			producto=productoBusiness.obtener(producto.getIdProducto());
			//para verificar que no existe
			Assert.assertNotNull(producto);
			System.out.println("ID: " + producto.getIdProducto());
			System.out.println("Nombre: " + producto.getNombre());
			System.out.println("Precio: " + producto.getPrecio());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	
	public void d_listar() {
		try {
			System.out.println("Método Listar");
			List<Producto> lista=productoBusiness.listar();
			//para verificar si existe.
			Assert.assertTrue(lista.size() > 0);
			for (Producto producto : lista) {
				System.out.println("ID: " + producto.getIdProducto());
				System.out.println("Nombre: " + producto.getNombre());
				System.out.println("Precio: " + producto.getPrecio());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void e_eliminar() {
		try {
			System.out.println("Método Eliminar");
			productoBusiness.eliminar(producto);
			Producto productoEliminado=productoBusiness.obtener(producto.getIdProducto());
			Assert.assertNull(productoEliminado);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
