package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.driver.VisorDriver;

public class ProductoPage {
	private By linkMenu = By.xpath("/html/body/section/div[1]/div");
	private By linkModAlmacen = By.xpath("/html/body/section/div[1]/nav/ul/li/span");
	private By linkMntProducto = By.linkText("Mnt. de Productos");
	private By botonNuevo = By.id("btnNuevo");
	private By cajaNombre = By.id("txtNombre");
	private By cajaPrecio = By.id("txtPrecio");
	private By botonGuardar = By.id("btnGuardar");
	private By mensajeRespuesta = By.id("messages");
	private WebDriver webDriver = null;

	public ProductoPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public String insertar(String nombre, Double precio) throws Exception {
		//TODO
		Thread.sleep(2000);
		this.webDriver.findElement(linkMenu).click();
		
		Thread.sleep(2000);
		this.webDriver.findElement(linkModAlmacen).click();
		
		Thread.sleep(2000);
		this.webDriver.findElement(linkMntProducto).click();
		
		Thread.sleep(2000);
		this.webDriver.findElement(botonNuevo).click();
		
		Thread.sleep(2000);
		this.webDriver.findElement(cajaNombre).clear();
		this.webDriver.findElement(cajaNombre).sendKeys(nombre);
		
		Thread.sleep(2000);
		this.webDriver.findElement(cajaPrecio).clear();
		this.webDriver.findElement(cajaPrecio).sendKeys(String.valueOf(precio));
		
		this.webDriver.findElement(botonGuardar).click();
		Thread.sleep(2000);
		
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void cerrarPagina(){
		VisorDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
