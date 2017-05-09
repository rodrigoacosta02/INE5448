package test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.utilidades.Sorteio;

public class TesteSorteio {

	@Test(expected = IllegalArgumentException.class)
	public void testValorNegativo() throws Exception {
		Sorteio.sortear(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@Test
	public void testvaloresPositivos() throws Exception {
		int zero = 0;
		int maximoValor = Integer.MAX_VALUE;
		int sorteio = Sorteio.sortear(zero, maximoValor);
		assertTrue("Valor sorteado " + sorteio, sorteio >= zero && sorteio <= maximoValor);
	}

}
