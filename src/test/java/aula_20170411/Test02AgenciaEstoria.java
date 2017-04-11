package aula_20170411;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

@FixtureSetup(Test01BancoEstoria.class)
public class Test02AgenciaEstoria {
	@Fixture
	private SistemaBancario sistemaBancario;
	@Fixture
	private Banco bancoDoBrasil;

	private Agencia bancoDoBrasilCentro;

	@Before
	public void configurar() throws Exception {
		bancoDoBrasilCentro = bancoDoBrasil.criarAgencia(Constantes.CENTRO);
	}

	@Test
	public void criacaoAgencia() throws Exception {
		assertEquals("001", bancoDoBrasilCentro.obterIdentificador());
		assertEquals(Constantes.CENTRO, bancoDoBrasilCentro.obterNome());
		assertEquals(bancoDoBrasil, bancoDoBrasilCentro.obterBanco());
	}
}