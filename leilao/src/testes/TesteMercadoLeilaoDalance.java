package testes;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import interfaces.IMercadoLeilao;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;

public class TesteMercadoLeilaoDalance {

	private final static String PRODUTO_1 = "Produto 1";
	private final static String CPF_COMPRADOR = "01234567890";
//	private final static String PRODUTO_1 = "Produto 1";
	
	private MercadoLeilao ml;

	@Before
	public void before() {
		ml = new MercadoLeilao();
		cadastrarUsuarios();
		cadastrarProdutosEmLeilao();
	}

	private void cadastrarProdutosEmLeilao() {
		try {
			ml.cadastrarProduto(PRODUTO_1, "produto descrição", 0.1, "01234567890", new Date(new Date().getTime() +10 ));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cadastrarUsuarios() {
		try {
			ml.cadastrarUsuario("User 1", "Rua das Flores", "a@a.com", "01234567890");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void testNaoExisteProduto() throws Exception {
		ml.daLance("A", "", 30.1);
	}
	
	
	@Test(expected = Exception.class)
	public void testValorInferior() throws Exception {
		ml.daLance(PRODUTO_1, CPF_COMPRADOR, 0.0);
	}
	
	@Test
	public void testLance() throws Exception {
		ml.daLance(PRODUTO_1, CPF_COMPRADOR, 30.1);
//		assertTrue(ml.getProdutosVendidos().size() == 1);
	}

	
	
}
