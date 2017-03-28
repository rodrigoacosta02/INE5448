package aula_20170328.agil.inf.ufsc.br;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

public class TestFaltaErroFalha {

	@Test
	public void teste1() {
		assertEquals(0, 1 / 0);
	}

	@Test(expected = InvalidParameterException.class)
	public void teste2() {
		assertEquals(0, 1 / 0);
	}

	@Test(expected = InvalidParameterException.class)
	public void teste3() {
		assertEquals(2, 2);
	}

	@Test
	public void teste4() {
		assertEquals(1, 2);
	}

}
