package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.fuenteDatos.Excel;
import pe.com.test.seleniumwd.fuenteDatos.MySql;
import pe.com.test.seleniumwd.page.CategoriaPage;
import pe.com.test.seleniumwd.page.IniciarSesionPage;
import pe.com.test.seleniumwd.util.Utilitario;

public class CategoriaWebDriverTest {

	private String urlInicial = "http://localhost:8082/VisorWeb/";
	private CategoriaPage categoriaPage;
	private IniciarSesionPage iniciarSesionPage;
	private String rutaCarpeta ="C:\\capturapantalla\\Categorias";
	
	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) throws Exception {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, this.urlInicial, remoto == 1);
		this.categoriaPage = new CategoriaPage(this.iniciarSesionPage.getWebDriver());
	}

	@DataProvider(name = "datosEntrada")
	public static Object[][] datosPoblados(ITestContext context) {
		Object[][] datos = null;
		//TODO
		//el archivo esta en fuente datos
		String fuenteDatos=context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos : " + fuenteDatos);
		
		switch (fuenteDatos) {
		case "BD":
			datos=MySql.leerCategoriaMysql();
			break;
		case "Excel":
			String rutaExcel=context.getCurrentXmlTest().getParameter("rutaArchivo");
			datos=Excel.leerExcel(rutaExcel);
			break;
		}
		return datos;
	}

	@Test(dataProvider = "datosEntrada")
	public void insertarCategoria(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = categoriaPage.insertar(nombre.trim());
			
			Assert.assertEquals(valorObtenido, valorEsperado);
			
		}catch(AssertionError error)
		{
			Utilitario.
			caputarPantallarError(
					rutaCarpeta, 
					error.getMessage(), 
					categoriaPage.getWebDriver());	
			Assert.fail();
		
		
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dataProvider="datosEntrada")
	public void actualizarCategoria(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
		try {
		iniciarSesionPage.iniciarSesion(usuario, clave);
		String valorObtenido=categoriaPage.actualizar(nombre.trim());
		
		Assert.assertEquals(valorObtenido, valorEsperado);

		}catch(AssertionError error)
		{
		Utilitario.caputarPantallarError(rutaCarpeta,error.getMessage(),categoriaPage.getWebDriver());
		Assert.fail();
		}catch(Exception e){
		e.printStackTrace();
		Assert.fail();
	}
	}
	//Flujo Actualizar Alternativo 1: Se deja el campo nombre vacío y se  muestra mensaje de advertencia
	@Test(dataProvider="datosEntrada")
	public void actualizarCategoria_FlujoAlternativo(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
		try {
		iniciarSesionPage.iniciarSesion(usuario, clave);
		String valorObtenido=categoriaPage.actualizar("");
		
		Assert.assertEquals(valorObtenido, valorEsperado);

		}catch(AssertionError error)
		{
		Utilitario.caputarPantallarError(rutaCarpeta,error.getMessage(),categoriaPage.getWebDriver());
		Assert.fail();
		}catch(Exception e){
		e.printStackTrace();
		Assert.fail();
	}
	}
	
	//Flujo Actualizar Alternativo 2: No se selecciona ningún elemento para editar de la grilla de búsqueda
		@Test(dataProvider="datosEntrada")
		public void actualizarCategoria_FlujoAlternativo2(String usuario, String clave, String nombre, String valorEsperado) throws Exception {
			try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido=categoriaPage.actualizar("");
			
			Assert.assertEquals(valorObtenido, valorEsperado);

			}catch(AssertionError error)
			{
			Utilitario.caputarPantallarError(rutaCarpeta,error.getMessage(),categoriaPage.getWebDriver());
			Assert.fail();
			}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
		}
	
	@AfterTest
	public void tearDown() throws Exception {
		categoriaPage.cerrarPagina();
	}

}
