package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.page.IniciarSesionPage;
import pe.com.test.seleniumwd.page.ProductoPage;

public class ProductoWebDriverTest {
	private String urlInicial = "http://localhost:8082/VisorWeb/";
	private ProductoPage productoPage;
	private IniciarSesionPage iniciarSesionPage;

	@BeforeTest
	@Parameters({"navegador", "remoto"})
	public void inicioClase(String navegador, int remoto) throws Exception {
		boolean esRemoto=Boolean.parseBoolean(String.valueOf(remoto));
		//Debemos usar IniciarSesionPage ya que es null
		iniciarSesionPage =new IniciarSesionPage(navegador,urlInicial,esRemoto);
		productoPage=new ProductoPage(iniciarSesionPage.getWebDriver());
		
	}
	@Test
	public void insertarProducto_FlujoBasico() throws Exception {
		try {
			String valorEsperado = "Se guardó de manera correcta el Producto";
			iniciarSesionPage.iniciarSesion("admin", "clave");
			String valorObtenido = productoPage.insertar("Nuevo Producto POM", 3.0);
			Assert.assertEquals(valorEsperado , valorObtenido);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(dependsOnMethods = {"insertarProducto_FlujoBasico"})
	public void insertarProducto_FlujoAlternativo() throws Exception {
		try {
			iniciarSesionPage.iniciarSesion("admin", "clave");
			String valorEsperado = "Nombre: Error de validación: se necesita un valor.";
			String valorObtenido = productoPage.insertar("",3.2);
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@AfterTest
	public void tearDown() throws Exception {
		productoPage.cerrarPagina();
	}
}
