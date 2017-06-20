package acceptanceTests.cucumberSteps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import modelo.FachadaMercadoLeilaoComSerializacao;

@StepDefAnnotation
@CucumberOptions(glue = "acceptanceTests.cucumberSteps", features = "AppLeilao/src/test/java/features/CadastrarProduto_Test.feature", monochrome = true)
public class CadastrarProdutoTest {

	public String nome = "";
	public String descricao = "";
	public Date dataLimite;
	public String cpfLeiloador = "";
	public Double lanceMinimo = 0.0;
	public Boolean expectedResult = true;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Before
	public void setup() throws Exception {
		this.fachada.limparMercado();
		this.fachada.cadastrarUsuario("Nome", "Descricao", "asdf@asdf.com", "327.387.790-18");
		this.fachada.cadastrarProduto("carro B", "Descr 1", 500.0, "327.387.790-18", new Date());
	}

	@After
	public void tearDown() {
		this.fachada.montarMercado();
		this.fachada.limparMercado();
	}

	@Given("^O nome do produto \"([^\"]*)\"$")
	public void o_nome_do_produto(String arg1) {
		this.nome = arg1;
	}

	@Given("^a descricao \"([^\"]*)\"$")
	public void a_descricao(String arg1) {
		this.descricao = arg1;
	}

	@Given("^e a data limite \"([^\"]*)\"$")
	public void e_a_data_limite(String arg1) {
		try {
			this.dataLimite = new SimpleDateFormat("yyyy-MM-dd").parse(arg1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Given("^e o cpf leiloador \"([^\"]*)\"$")
	public void e_o_cpf_leiloador(String arg1) throws Throwable {
		this.cpfLeiloador = arg1;
	}

	@Given("^e o lance minimo \"([^\"]*)\"$")
	public void e_o_lance_minimo(String arg1) throws Throwable {
		this.lanceMinimo = Double.valueOf(arg1);
	}

	@When("^o produto cadastrado com \"([^\"]*)\"$")
	public void o_produto_cadastrado_com(String arg1) {
		boolean expectedResult = Boolean.parseBoolean(arg1);

		Boolean cadastro = false;
		try {
			this.fachada.cadastrarProduto(this.nome, this.descricao, this.lanceMinimo, this.cpfLeiloador,
					this.dataLimite);
			cadastro = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertEquals(expectedResult, cadastro);
	}

	@Then("^o produto deve \"([^\"]*)\"$")
	public void o_produto_deve(String arg1) {
		boolean expectedResult = Boolean.parseBoolean(arg1);
		Boolean produtoExiste = this.fachada.verificaSeOProdutoJaExiste(this.nome);

		Assert.assertEquals(expectedResult, produtoExiste);

	}
}
