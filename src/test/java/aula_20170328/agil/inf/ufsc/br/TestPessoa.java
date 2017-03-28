package aula_20170328.agil.inf.ufsc.br;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPessoa {

	@Test
	public void testAssertEquals() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertEquals("João", joao.obterNome());
	}

	@Test
	public void testAssertNotEquals() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertNotEquals("Maria", joao.obterNome());
	}

	@Test
	public void testArrayEquals() {
		String[] nomes = { "João", "Maria" };
		assertArrayEquals(new String[] { "João", "Maria" }, nomes); // true
		assertArrayEquals(new String[] { "Maria", "João" }, nomes); // false
	}

	@Test
	public void testAssertTrue() {
		Pessoa maria = new Pessoa(1, "Maria", 25, Ocupacao.PROFESSORA);
		assertTrue(maria.maiorDeIdade());
	}

	@Test
	public void testAssertFalse() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertFalse(joao.maiorDeIdade());
	}

	@Test
	public void testAssertNull() {
		Pessoa joao = null;
		assertNull(joao);
	}

	@Test
	public void testAssertNotNull() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertNotNull(joao);
	}

	@Test
	public void testAssertSame() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertEquals(joao, joao);
	}

	@Test
	public void testAssertNotSame() {
		Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
		assertNotSame(joao, new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE));
	}

	@Test(expected = Exception.class)
	public void testExpectedException() {
		fail("Não deveria deixar criar uma pessoa com idade negativa.");
	}

	@Test
	public void testFail() {
		try {
			new Pessoa(1, "João", -1, Ocupacao.ESTUDANTE);
			fail("Não deveria deixar criar uma pessoa com idade negativa.");
		} catch (Exception excecao) {
		}
	}

}
