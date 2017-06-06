package br.ufsc.teste.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Teste {

	private static WebDriver driver;

	@BeforeClass
	public static void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", "/home/100000000243152/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://www.calculatoria.com/");
	}

	@Before
	public void beforeTest() {
		this.clearDisplay();
	}

	@Test
	public void VinteVezesQuatro() throws Exception {
		this.clicarNosBotoes(Botoes.DOIS, Botoes.ZERO);
		this.clicarNosBotoes(Botoes.MULTIPLICACAO);
		this.clicarNosBotoes(Botoes.QUATRO);
		this.clicarNosBotoes(Botoes.IGUAL);
		// this.delay(5000);

		assertEquals("80", this.getResultadoDisplay());
	}

	@Test
	public void cemDivididoPorTres() throws Exception {
		this.clicarNosBotoes(Botoes.UM, Botoes.ZERO, Botoes.ZERO);
		this.clicarNosBotoes(Botoes.DIVISAO);
		this.clicarNosBotoes(Botoes.TRES);
		this.clicarNosBotoes(Botoes.IGUAL);
		// this.delay(5000);

		assertEquals("33.333", this.getResultadoDisplay());
	}

	@Test
	public void cincoDivididoPorDois() throws Exception {
		this.cincoDivididoPorDoisPasso();

		assertEquals("2.5", this.getResultadoDisplay());
	}

	@Test
	public void cincoDivididoPorDoisApagaDecimaisSomaQuatro() throws Exception {
		this.cincoDivididoPorDoisPasso();
		this.clicarNosBotoes(Botoes.DEL, Botoes.DEL);
		this.clicarNosBotoes(Botoes.SOMA);
		this.clicarNosBotoes(Botoes.QUATRO);
		this.clicarNosBotoes(Botoes.IGUAL);

		assertEquals("6", this.getResultadoDisplay());
	}

	@AfterClass
	public static void after() {
		Teste.driver.quit();
	}

	/*
	 * auxiliares
	 */
	private void cincoDivididoPorDoisPasso() {
		this.clicarNosBotoes(Botoes.CINCO);
		this.clicarNosBotoes(Botoes.DIVISAO);
		this.clicarNosBotoes(Botoes.DOIS);
		this.clicarNosBotoes(Botoes.IGUAL);
	}

	private String getResultadoDisplay() {
		return Teste.driver.findElement(By.className("displaysum")).getAttribute("value");
	}

	private void clicarNosBotoes(String... botoes) {
		for (String botao : botoes) {
			Teste.driver.findElement(By.id(botao)).click();
		}
	}

	private void clearDisplay() {
		this.clicarNosBotoes(Botoes.CLEAR);

	}

	private void delay(int milis) throws InterruptedException {
		Thread.sleep(5000); // Let the user actually see something!
	}

	interface Botoes {
		String ZERO = "btn96";
		String UM = "btn97";
		String DOIS = "btn98";
		String TRES = "btn99";
		String QUATRO = "btn100";
		String CINCO = "btn101";

		String SOMA = "btn107";
		String MULTIPLICACAO = "btn106";
		String DIVISAO = "btn111";
		String IGUAL = "btn13";
		String CLEAR = "btn27";
		String DEL = "btn8";
	}
}
