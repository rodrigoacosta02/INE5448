package aula_20170328;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

import static org.junit.Assert.assertThat;

import org.junit.Before;

public class TesteHamcrestCustomMatcher {
	private static final String BANCO_DO_BRASIL = "Banco do Brasil";
	private static final String CENTRO = "Centro";
	private static final String MARIA = "Maria";
	private static final String IDENTIFICADOR = "0001-5";
	SistemaBancario sistemaBancario;
	Banco bb;
	Agencia bbCentro;
	Conta mariaConta;

	@Before
	public void before() {
		this.sistemaBancario = new SistemaBancario();
		this.bb = this.sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		this.bbCentro = this.bb.criarAgencia(CENTRO);
		this.mariaConta = this.bbCentro.criarConta(MARIA);
	}

	@Test
	public void testName() throws Exception {

		assertThat(this.mariaConta, new CombinadorDeConta(IDENTIFICADOR, MARIA, BANCO_DO_BRASIL, CENTRO));
	}
}
