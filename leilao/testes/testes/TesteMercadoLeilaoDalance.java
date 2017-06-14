package testes;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelo.Lance;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;

public class TesteMercadoLeilaoDalance {

	private static final String NOME_COMPRADOR_1 = "User 1";
	private final static String PRODUTO_1 = "Produto 1";
	private final static String CPF_COMPRADOR_1 = "01234567890";
	private final static Double LANCE_MINIMO = 0.1;

	private MercadoLeilao ml;

	@Before
	public void before() throws Exception {
		this.ml = new MercadoLeilao();
		this.cadastrarUsuarios(NOME_COMPRADOR_1, CPF_COMPRADOR_1);
		this.cadastrarProdutosEmLeilao();
	}

	private void cadastrarProdutosEmLeilao() throws Exception {
		this.ml.cadastrarProduto(PRODUTO_1, "produto descrição", LANCE_MINIMO, "01234567890",
				new Date(new Date().getTime() + 10));
	}

	private void cadastrarUsuarios(String usuario, String cpf) throws Exception {
		this.ml.cadastrarUsuario(usuario, "Rua das Flores", "a@a.com", cpf);
	}

	/*
	 * TESTES
	 */

	@Test(expected = Exception.class)
	public void naoExisteProduto() throws Exception {
		this.ml.daLance("A", CPF_COMPRADOR_1, 30.1);
	}

	@Test(expected = Exception.class)
	public void naoExisteComprador() throws Exception {
		this.ml.daLance(PRODUTO_1, "", 0.0);
	}

	@Test(expected = Exception.class)
	public void valorInferior() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 0.0);
	}

	@Test(expected = Exception.class)
	public void doisLancesSegundoInferiorAoPrimeiro() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.5);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.0);
	}

	@Test(expected = Exception.class)
	public void doisLancesMesmoUsuarioIguais() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.0);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.0);
	}

	@Test
	public void doisLancesMesmoUsuario() throws Exception {
		Double valorLance_2 = 1.5;

		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.0);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, valorLance_2);

		ProdutoLeilao produtoEmLeilao = (ProdutoLeilao) this.ml.getProdutosEmLeilao().get(0);
		modelo.Lance lance_1 = this.ml.retornaTodosOsLancesEfetuados().get(0);
		modelo.Lance lance_2 = this.ml.retornaTodosOsLancesEfetuados().get(1);

		assertEquals(NOME_COMPRADOR_1, lance_1.getNomeDonoDoLance());
		assertEquals(NOME_COMPRADOR_1, lance_2.getNomeDonoDoLance());
		assertEquals(PRODUTO_1, lance_1.getNomeProdutoQueRecebeuOLance());
		assertEquals(PRODUTO_1, lance_2.getNomeProdutoQueRecebeuOLance());

		assertEquals(valorLance_2, produtoEmLeilao.retornaTodosOsLancesFeitosNesseProduto().get(1).getValorDoLance());
	}

	@Test
	public void doisLancesDoisUsuario() throws Exception {
		Double valorLance_2 = 1.5;
		String nomeComprador_2 = "User 2";
		String cpfComprador_2 = "09876543210";

		this.cadastrarUsuarios(nomeComprador_2, cpfComprador_2);
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, 1.0);
		this.ml.daLance(PRODUTO_1, cpfComprador_2, valorLance_2);

		ProdutoLeilao produtoEmLeilao = (ProdutoLeilao) this.ml.getProdutosEmLeilao().get(0);
		List<Lance> retornaLances = this.ml.retornaTodosOsLancesEfetuados();
		Usuario user_1 = (Usuario) this.ml.getUsuariosCadastrados().get(0);
		Usuario user_2 = (Usuario) this.ml.getUsuariosCadastrados().get(1);

		assertEquals(user_1.getNome(), retornaLances.get(1).getNomeDonoDoLance());
		assertEquals(user_1.getCpf(), retornaLances.get(1).getCpfDonoDoLance());
		assertEquals("a@a.com", user_1.getEmail());
		assertEquals("Rua das Flores", user_1.getEndereco());
		assertEquals(0, user_1.getBensComprados().size());
		assertEquals(0, user_1.getBensOfertados().size());

		assertEquals(user_2.getNome(), retornaLances.get(0).getNomeDonoDoLance());
		assertEquals(user_2.getCpf(), retornaLances.get(0).getCpfDonoDoLance());
		assertEquals("a@a.com", user_1.getEmail());
		assertEquals("Rua das Flores", user_2.getEndereco());
		assertEquals(0, user_2.getBensComprados().size());
		assertEquals(1, user_2.getBensOfertados().size());

		assertEquals(PRODUTO_1, retornaLances.get(0).getNomeProdutoQueRecebeuOLance());
		assertEquals(valorLance_2, produtoEmLeilao.retornaTodosOsLancesFeitosNesseProduto().get(1).getValorDoLance());
		assertEquals(PRODUTO_1, retornaLances.get(0).getNomeProdutoQueRecebeuOLance());

	}

	@Test
	public void lance() throws Exception {
		Double maxValue = Double.MAX_VALUE;
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, maxValue);
		modelo.Lance lance = this.ml.retornaTodosOsLancesEfetuados().get(0);
		ProdutoLeilao produtoEmLeilao = (ProdutoLeilao) this.ml.getProdutosEmLeilao().get(0);

		assertEquals(PRODUTO_1, lance.getNomeProdutoQueRecebeuOLance());
		assertEquals(NOME_COMPRADOR_1, lance.getNomeDonoDoLance());
		assertEquals(maxValue, produtoEmLeilao.retornaTodosOsLancesFeitosNesseProduto().get(0).getValorDoLance());

	}

	@Test
	public void lanceMinimo() throws Exception {
		this.ml.daLance(PRODUTO_1, CPF_COMPRADOR_1, LANCE_MINIMO);
		modelo.Lance lance = this.ml.retornaTodosOsLancesEfetuados().get(0);
		ProdutoLeilao produtoEmLeilao = (ProdutoLeilao) this.ml.getProdutosEmLeilao().get(0);

		assertEquals(PRODUTO_1, lance.getNomeProdutoQueRecebeuOLance());
		assertEquals(NOME_COMPRADOR_1, lance.getNomeDonoDoLance());
		assertEquals(LANCE_MINIMO, produtoEmLeilao.retornaTodosOsLancesFeitosNesseProduto().get(0).getValorDoLance());

	}

}
