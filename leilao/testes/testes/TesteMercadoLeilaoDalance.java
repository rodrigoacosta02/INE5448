package testes;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import modelo.MercadoLeilao;

public class TesteMercadoLeilaoDalance {

	private final static String PRODUTO_1 = "Produto 1";
	private final static String CPF_COMPRADOR = "01234567890";
	// private final static String PRODUTO_1 = "Produto 1";

	private MercadoLeilao ml;

	@Before
	public void before() {
		this.ml = new MercadoLeilao();
		this.cadastrarUsuarios("User 1", CPF_COMPRADOR);
		this.cadastrarProdutosEmLeilao();
	}

	private void cadastrarProdutosEmLeilao() {
		try {
			this.ml.cadastrarProduto(PRODUTO_1, "produto descrição", 0.1, "01234567890",
					new Date(new Date().getTime() + 10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cadastrarUsuarios(String usuario, String cpf) {
		try {
			this.ml.cadastrarUsuario(usuario, "Rua das Flores", "a@a.com", cpf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testNaoExisteProduto() throws Exception {
		this.ml.daLance("A", CPF_COMPRADOR, 30.1);
	}

	@Test(expected = Exception.class)
	public void testNaoExisteComprador() throws Exception {
		this.ml.daLance(PRODUTO_1, "", 0.0);
	}

	@Test(expected = Exception.class)
	public void testValorInferior() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 0.0);
	}

	@Test(expected = Exception.class)
	public void testDoisLancesSegundoInferiorAoPrimeiro() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 1.5);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 1.0);
	}

	@Test
	public void testDoisLancesMesmoUsuario() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 1.0);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 1.5);

		assertTrue(this.ml.getProdutosQueDeuLance(CPF_COMPRADOR).size() == 1);
		assertTrue(this.ml.retornaLancesDeUmUsuario(CPF_COMPRADOR).size() == 2);
	}

	@Test
	public void testDoisLancesDoisUsuario() throws Exception {
		String user2 = "User 2";
		String cpf2 = "09876543210";
		this.cadastrarUsuarios(user2, cpf2);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, 1.0);
		this.ml.daLance(PRODUTO_1, cpf2, 1.5);

		assertTrue(this.ml.getProdutosQueDeuLance(CPF_COMPRADOR).size() == 1);
		assertTrue(this.ml.getProdutosQueDeuLance(CPF_COMPRADOR).size() == 1);
		assertTrue(this.ml.retornaLancesDeUmUsuario(cpf2).size() == 1);
		assertTrue(this.ml.retornaLancesDeUmUsuario(cpf2).size() == 1);
	}

	@Test
	public void testLance() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR, Double.MAX_VALUE);
		assertTrue(this.ml.getProdutosQueDeuLance(CPF_COMPRADOR).size() == 1);
		assertTrue(this.ml.retornaLancesDeUmUsuario(CPF_COMPRADOR).size() == 1);
	}

}
