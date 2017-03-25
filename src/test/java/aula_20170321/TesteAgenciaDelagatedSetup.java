package aula_20170321;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

/*
 * Delegated setup
 */
public class TesteAgenciaDelagatedSetup {

	private static final String BANCO_DO_BRASIL = "Banco do Brasil";
	private static final String CENTRO = "Centro";
	private static final String MARIA = "Maria";

	private SistemaBancario getSistemaBancario() {
		SistemaBancario sistemaBancario = new SistemaBancario();
		return sistemaBancario;
	}

	private Banco novoBanco() {
		SistemaBancario sistemaBancario = this.getSistemaBancario();
		Banco bb = sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		return bb;
	}

	private Agencia novaAgencia() {
		Agencia bbCentro = this.novoBanco().criarAgencia(CENTRO);
		return bbCentro;
	}

	private Conta novaConta() {
		Conta mariaConta = this.novaAgencia().criarConta(MARIA);
		return mariaConta;
	}

	private Conta novaContaComDezReaisDeSaldo() {
		Conta mariaConta = this.novaConta();
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		this.getSistemaBancario().depositar(mariaConta, dezReais);
		return mariaConta;
	}

	private Conta novaContaComQuatroReaisDeSaldo() {
		Conta mariaConta = this.novaConta();
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 4, 0);
		this.getSistemaBancario().depositar(mariaConta, dezReais);
		return mariaConta;
	}

	// início testes

	@Test
	public void criacaoDeBanco() throws Exception {
		Banco bb = this.novoBanco();

		assertEquals(BANCO_DO_BRASIL, bb.obterNome());
		assertEquals(Moeda.BRL, bb.obterMoeda());
	}

	@Test
	public void criacaoAgencia() throws Exception {
		Agencia bbCentro = this.novaAgencia();

		assertEquals("001", bbCentro.obterIdentificador());
		assertEquals(CENTRO, bbCentro.obterNome());
		assertEquals(BANCO_DO_BRASIL, bbCentro.obterBanco().obterNome());
	}

	@Test
	public void criacaoDeConta() throws Exception {
		Conta mariaConta = this.novaConta();

		assertEquals("0001-5", mariaConta.obterIdentificador());
		assertEquals(MARIA, mariaConta.obterTitular());
		assertEquals("0,00", mariaConta.calcularSaldo().formatado());
		assertEquals(CENTRO, mariaConta.obterAgencia().obterNome());
	}

	@Test
	public void operacaoDeposito() throws Exception {
		Conta mariaConta = this.novaConta();
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		Operacao deposito = this.getSistemaBancario().depositar(mariaConta, dezReais);

		assertEquals(EstadosDeOperacao.SUCESSO, deposito.obterEstado());
		assertEquals(dezReais.toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaque() throws Exception {
		Conta mariaConta = this.novaContaComDezReaisDeSaldo();
		Operacao operacao = this.getSistemaBancario().sacar(mariaConta, new Dinheiro(Moeda.BRL, 6, 0));

		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(new Dinheiro(Moeda.BRL, 4, 0).toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaqueInsuficiente() throws Exception {
		Conta mariaConta = this.novaContaComQuatroReaisDeSaldo();
		Dinheiro seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		Operacao operacao = this.getSistemaBancario().sacar(mariaConta, seisReais);

		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, operacao.obterEstado());
		assertEquals(new Dinheiro(Moeda.BRL, 4, 0).toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}
}
