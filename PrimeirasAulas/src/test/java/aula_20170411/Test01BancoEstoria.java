package aula_20170411;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class Test01BancoEstoria {

	private SistemaBancario sistemaBancario;
	private Banco bancoDoBrasil;
	
	@Before
	public void configurar() throws Exception {
		sistemaBancario = new SistemaBancario();
		bancoDoBrasil = sistemaBancario.criarBanco(Constantes.BANCO_DO_BRASIL, Moeda.BRL);
	}

	@Test
	public void criacaoDeBanco() throws Exception {
		assertEquals(Constantes.BANCO_DO_BRASIL, bancoDoBrasil.obterNome());
		assertEquals(Moeda.BRL, bancoDoBrasil.obterMoeda());
}
}
