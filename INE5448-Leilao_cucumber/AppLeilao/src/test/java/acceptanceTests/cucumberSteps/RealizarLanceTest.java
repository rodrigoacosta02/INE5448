package acceptanceTests.cucumberSteps;

import java.util.Date;
import java.util.List;

import org.junit.Assert;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import interfaces.ILeiloavel;
import modelo.FachadaMercadoLeilaoComSerializacao;

@StepDefAnnotation
@CucumberOptions(glue = "acceptanceTests.cucumberSteps", features = "AppLeilao/src/test/java/features/RealizarLance_Test.feature", monochrome = true)
public class RealizarLanceTest {

	public String nomeProduto = "";
	public String cpfComprador = "";
	public Double valorLance = 0.0;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Before
	public void setup() throws Exception {
		this.fachada.limparMercado();
		this.fachada.cadastrarUsuario("Nome", "Descricao", "asdf@asdf.com", "327.387.790-18");
		this.fachada.cadastrarProduto("casa", "Descr 1", 1500.0, "327.387.790-18", new Date());
	}

	@After
	public void tearDown() {
		this.fachada.montarMercado();
		this.fachada.limparMercado();
	}

	@Given("^O nome produto \"([^\"]*)\"$")
	public void o_nome_produto(String arg1) {
		this.nomeProduto = arg1;
	}

	@Given("e o cpf do comprador \"([^\"]*)\"$")
	public void e_o_cpf_do_comprador(String arg1) throws Throwable {
		this.cpfComprador = arg1;
	}

	@Given("^e o valor do lance \"([^\"]*)\"$")
	public void e_o_lance_minimo(String arg1) throws Throwable {
		this.valorLance = Double.valueOf(arg1);
	}

	@When("^o lance efetuado com \"([^\"]*)\"$")
	public void o_lance_efetuado_com(String arg1) {
		boolean expectedResult = Boolean.parseBoolean(arg1);

		Boolean cadastro = false;
		try {
			this.fachada.daLance(this.nomeProduto, this.cpfComprador, this.valorLance);
			cadastro = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(expectedResult, cadastro);
	}

	@Then("^o lance efetuado pelo usuario \"([^\"]*)\"$")
	public void o_lance_efetuado_pelo_usuario(String arg1) throws Exception {
		boolean expectedResult = Boolean.parseBoolean(arg1);
		List<? extends ILeiloavel> produtosQueDeuLance = this.fachada.getProdutosQueDeuLance(this.cpfComprador);
		boolean lanceEfetuadoPorUsuario = false;
		for (ILeiloavel iLeiloavel : produtosQueDeuLance) {
			if (iLeiloavel.getNome().equals(this.nomeProduto)) {
				lanceEfetuadoPorUsuario = true;
				break;
			}
		}

		Assert.assertEquals(expectedResult, lanceEfetuadoPorUsuario);

	}
}
