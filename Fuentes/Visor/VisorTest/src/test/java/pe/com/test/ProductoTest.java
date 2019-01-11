package pe.com.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import pe.com.core.business.CategoriaBusiness;
import pe.com.core.business.ProductoBusiness;
//import pe.com.core.entity.Categoria;
import pe.com.core.entity.Producto;
//import pe.com.test.bean.CategoriaBean;
import pe.com.test.bean.ProductoBean;

public class ProductoTest {
	
	private final ProductoBusiness productoBusiness = new ProductoBusiness();
	private static Producto producto;
	
	@BeforeClass
	public void inicioClase() {
		System.out.println("**********************Inicio Clase ProductoTest**********************");
	}

	@AfterClass
	public void finClase() {
		System.out.println("**********************Fin Clase ProductoTest**********************");
	}

	@BeforeTest
	public void inicioMetodo() {
		System.out.println("**********************Inicio Metodo ProductoTest**********************");
	}

	@AfterTest
	public void finMetodo() {
		System.out.println("Id Producto: " + producto.getIdProducto());
		System.out.println("Nombre: " + producto.getNombre());
		System.out.println("**********************Fin Metodo ProductoTest**********************\n\n");
	}
	
//Indicamos que es mi proveedor de datos
	@DataProvider(name="datosEntrada")
	public static Object[][] cargarDatos() {
	return new Object[][] {
		{ new ProductoBean("Oreo", 0.5,1)},
		{ new ProductoBean("Gloria", 0.5,1)}
	};
	}
	
	
	
	@Test(dataProvider="datosEntrada")
	public void insertarDataProvider(ProductoBean productoBean) {
		try{
			System.out.println("Metodo Insertar");
			producto=new Producto();
			producto.setNombre(productoBean.getNombre());
			producto.setPrecio(productoBean.getPrecio());
			producto.setIdCategoria(productoBean.getIdCategoria());
			productoBusiness.insertar(producto);
			Assert.assertTrue(producto.getIdProducto()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	@Parameters({"nombreInsertarP","precioInsertarP","IdCatInsertarP"})
	public void insertar(String nombreInsertar, Double precioInsertar, Integer idCatInsertar){
		try{
			System.out.println("Metodo Insertar");
			producto=new Producto();
			producto.setNombre(nombreInsertar);
			producto.setPrecio(precioInsertar);
			producto.setIdCategoria(idCatInsertar);
			productoBusiness.insertar(producto);
			
			Assert.assertTrue(producto.getIdProducto()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods={"insertar"})
	@Parameters({"nombreActualizarP", "precioActualizarP", "IdCatActualizarP"})
	public void actualizar(String nuevoNombre, Double nuevoPrecio, Integer nuevoIDCat){
		try{
			System.out.println("Metodo Actualizar");
			producto.setNombre(nuevoNombre);
			producto.setPrecio(nuevoPrecio);
			producto.setIdCategoria(nuevoIDCat);
			productoBusiness.actualizar(producto);
			Assert.assertTrue(producto.getIdProducto() > 0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods={"actualizar"})
	public void obtener(){
		try{
			System.out.println("Metodo Obtener");
			Producto productoBuscado = productoBusiness.obtener(producto.getIdProducto());
			Assert.assertEquals(producto, productoBuscado);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods="obtener")
	public void eliminar(){
		try{
			System.out.println("Metodo Eliminar");		
			productoBusiness.eliminar(producto);
			Producto productoEliminado=productoBusiness.obtener(producto.getIdProducto());
			Assert.assertNull(productoEliminado);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(timeOut=2000)
	public void metodoFueraTiempo(){
		try {
			System.out.println("Metodo metodoFueraTiempo");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}