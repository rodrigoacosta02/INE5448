package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.utilidades.Sorteio;

public class TesteSorteio {

	@Test(expected = IllegalArgumentException.class)
	public void testValorNegativoLimite() throws Exception {
		Sorteio.sortear(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@Test
	public void testValoresPositivosLimite() throws Exception {
		int zero = 0;
		int maximoValor = Integer.MAX_VALUE;
		int sorteio = Sorteio.sortear(zero, maximoValor);
		assertTrue("Valor sorteado " + sorteio, sorteio >= zero && sorteio <= maximoValor);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValorNegativoAbsMaiorPositivo() throws Exception {
		Sorteio.sortear(-500, 499);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValoresNegativosAbsMaiorMenor() throws Exception {
		int menorValor = -600;
		int maximoValor = -500;
		Sorteio.sortear(menorValor, maximoValor);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValoresPositivosMaiorMenor() throws Exception {
		int menorValor = 600;
		int maximoValor = 500;
		Sorteio.sortear(menorValor, maximoValor);
	}

	@Test
	public void testValoresNegativos() throws Exception {
		int menorValor = -500;
		int maximoValor = -600;
		int sorteio = Sorteio.sortear(menorValor, maximoValor);
		assertTrue("Valor sorteado " + sorteio, sorteio >= Math.abs(menorValor) && sorteio <= Math.abs(maximoValor));
	}

	@Test
	public void testValoresMenorPosMaiorNeg() throws Exception {
		int menorValor = 500;
		int maximoValor = -600;
		int sorteio = Sorteio.sortear(menorValor, maximoValor);
		assertTrue("Valor sorteado " + sorteio, sorteio >= Math.abs(menorValor) && sorteio <= Math.abs(maximoValor));
	}

	@Test
	public void testValoresPositivos() throws Exception {
		int zero = 0;
		int maximoValor = 500;
		int sorteio = Sorteio.sortear(zero, maximoValor);
		assertTrue("Valor sorteado " + sorteio, sorteio >= zero && sorteio <= maximoValor);
	}

}
