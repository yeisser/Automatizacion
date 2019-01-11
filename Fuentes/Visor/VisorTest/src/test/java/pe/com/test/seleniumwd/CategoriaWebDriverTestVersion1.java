package pe.com.test.seleniumwd;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;


public class CategoriaWebDriverTestVersion1 {
//Se declara una variable o un atributo de tipo WebDriver.
	//Este WebDriver es una interface, que me va a permitir seleccionar con que driver se va a trabajar.
	private WebDriver driver;
	private String urlInicial = "http://localhost:8082/VisorWeb/";
	
	@BeforeTest
	//BeforeTest permite ejecutar antes de el test.
	public void setUp() throws Exception {
		//seteamos el webdriver que se encuentra en la ruta indicada.
		//Este driver se puede descargar desde la misma página del Selenium.
		System.setProperty("webdriver.gecko.driver", "C:\\ProgramasInstalados\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver(); //Luego se importa a a Chrome.
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void insertarCategoria_FlujoBasico() throws Exception {
		try {
			String mensajeEsperado = "Se guardó de manera correcta la Categoría";
			
			driver.get(urlInicial);
			//El FindElement recibe un atributo de tipo By.
			//Por si las moscas lo limpio.
			driver.findElement(By.id("txtUsuario")).clear();
			driver.findElement(By.id("txtUsuario")).sendKeys("admin");
			
			WebElement txtClave=driver.findElement(By.id("txtClave"));
			txtClave.clear();
			txtClave.sendKeys("clave");
			
			driver.findElement(By.id("btnIniciarSesion")).click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("/html/body/section/div[1]/div")).click();
			driver.findElement(By.xpath("/html/body/section/div[1]/nav/ul/li/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Mnt. de Categoría")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnNuevo")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("txtNombre")).clear();
			driver.findElement(By.id("txtNombre")).sendKeys("Prueba Selenium");
			driver.findElement(By.id("btnGuardar")).click();
			Thread.sleep(2000);
			
			
			String mensajeObtenido = driver.findElement(By.id("messages")).getText();
			Assert.assertEquals(mensajeEsperado, mensajeObtenido);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods = {"insertarCategoria_FlujoBasico"})
	public void insertarCategoria_FlujoAlternativo() throws Exception {
		try {
			String mensajeEsperado = "Nombre: Error de validación: se necesita un valor.";
			//TODO Completar
			driver.get(urlInicial);
			//El FindElement recibe un atributo de tipo By.
			//Por si las moscas lo limpio.
			driver.findElement(By.id("txtUsuario")).clear();
			driver.findElement(By.id("txtUsuario")).sendKeys("admin");
			
			WebElement txtClave=driver.findElement(By.id("txtClave"));
			txtClave.clear();
			txtClave.sendKeys("clave");
			
			driver.findElement(By.id("btnIniciarSesion")).click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("/html/body/section/div[1]/div")).click();
			driver.findElement(By.xpath("/html/body/section/div[1]/nav/ul/li/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Mnt. de Categoría")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("btnNuevo")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("txtNombre")).clear();
			driver.findElement(By.id("txtNombre")).sendKeys("");
			driver.findElement(By.id("btnGuardar")).click();
			Thread.sleep(2000);
			
			
			String mensajeObtenido = driver.findElement(By.id("messages")).getText();
			Assert.assertEquals(mensajeEsperado, mensajeObtenido);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.close();
	}
}
