package pe.com.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.driver.VisorDriver;

public class IniciarSesionPage {

	private By cajaUsuario = By.id("txtUsuario");
	private By cajaClave = By.id("txtClave");
	private By botonIniciarSesion = By.id("btnIniciarSesion");
	private String urlInicial;
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, String urlInicial, boolean remoto){
		this.webDriver = VisorDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		this.webDriver.get(urlInicial);
		this.webDriver.findElement(cajaUsuario).clear();
		this.webDriver.findElement(cajaUsuario).sendKeys(usuario);
		this.webDriver.findElement(cajaClave).clear();
		this.webDriver.findElement(cajaClave).sendKeys(clave);
		this.webDriver.findElement(botonIniciarSesion).click();
	}
	
	public void cerrarPagina(){
		VisorDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
