package aula_20170321;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteAgenciaInLineSetup {
	private static final String BANCO_DO_BRASIL = "Banco do Brasil";
	private static final String CENTRO = "Centro";
	private static final String MARIA = "Maria";

	@Ignore
	public void caixaEconomicaTrindade() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco caixaEconomica = sistemaBancario.criarBanco("Caixa Econômica", Moeda.BRL);
		Agencia caixaEconomicaTrindade = caixaEconomica.criarAgencia("Trindade");
		assertEquals("001", caixaEconomicaTrindade.obterIdentificador());
		assertEquals("Trindade", caixaEconomicaTrindade.obterNome());
		assertEquals(caixaEconomica, caixaEconomicaTrindade.obterBanco());
	}

	/*
	 * in-line setup
	 */
	@Test
	public void criacaoDeBanco() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);

		assertEquals(BANCO_DO_BRASIL, bb.obterNome());
		assertEquals(Moeda.BRL, bb.obterMoeda());
	}

	@Test
	public void criacaoAgencia() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		Agencia bbCentro = bb.criarAgencia(CENTRO);

		assertEquals("001", bbCentro.obterIdentificador());
		assertEquals(CENTRO, bbCentro.obterNome());
		assertEquals(bb, bbCentro.obterBanco());
	}

	@Test
	public void criacaoDeConta() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		Agencia bbCentro = bb.criarAgencia(CENTRO);
		Conta mariaConta = bbCentro.criarConta(MARIA);

		assertEquals("0001-5", mariaConta.obterIdentificador());
		assertEquals(MARIA, mariaConta.obterTitular());
		assertEquals("0,00", mariaConta.calcularSaldo().formatado());
		assertEquals(CENTRO, mariaConta.obterAgencia().obterNome());
	}

	@Test
	public void operacaoDeposito() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		Agencia bbCentro = bb.criarAgencia(CENTRO);
		Conta mariaConta = bbCentro.criarConta(MARIA);
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		Operacao deposito = sistemaBancario.depositar(mariaConta, dezReais);

		assertEquals(EstadosDeOperacao.SUCESSO, deposito.obterEstado());
		assertEquals(dezReais.toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaque() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		Agencia bbCentro = bb.criarAgencia(CENTRO);
		Conta mariaConta = bbCentro.criarConta(MARIA);
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		sistemaBancario.depositar(mariaConta, dezReais);
		Operacao operacao = sistemaBancario.sacar(mariaConta, new Dinheiro(Moeda.BRL, 6, 0));

		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(new Dinheiro(Moeda.BRL, 4, 0).toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaqueInsuficiente() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		Agencia bbCentro = bb.criarAgencia(CENTRO);
		Conta mariaConta = bbCentro.criarConta(MARIA);
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		sistemaBancario.depositar(mariaConta, quatroReais);
		Operacao operacao = sistemaBancario.sacar(mariaConta, new Dinheiro(Moeda.BRL, 6, 0));

		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, operacao.obterEstado());
		assertEquals(quatroReais.toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}
}
