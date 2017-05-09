package aula_20170411;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

@FixtureSetup(Test02AgenciaEstoria.class)
public class Test03ContaEstoria {
	@Fixture
	private SistemaBancario sistemaBancario;	
	@Fixture
	private Banco bancoDoBrasil;
	@Fixture
	private Agencia bancoDoBrasilCentro;

	private Conta mariaConta;
	
	@Before
	public void configurar() throws Exception {
		mariaConta = bancoDoBrasilCentro.criarConta(Constantes.MARIA);
	}

	@Test
	public void criacaoDeConta() throws Exception {
		assertEquals("0001-5", mariaConta.obterIdentificador());
		assertEquals(Constantes.MARIA, mariaConta.obterTitular());
		assertEquals("0,00", mariaConta.calcularSaldo().formatado());
		assertEquals(Constantes.CENTRO, mariaConta.obterAgencia().obterNome());
	}

}