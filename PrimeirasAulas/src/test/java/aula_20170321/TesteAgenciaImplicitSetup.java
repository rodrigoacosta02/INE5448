package aula_20170321;

import static org.junit.Assert.*;

import org.junit.Before;
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
 * Implicit setup
 */
public class TesteAgenciaImplicitSetup {

	private static final String BANCO_DO_BRASIL = "Banco do Brasil";
	private static final String CENTRO = "Centro";
	private static final String MARIA = "Maria";

	SistemaBancario sistemaBancario;
	Banco bb;
	Agencia bbCentro;
	Conta mariaConta;
	Conta mariaContaDepositadoDezReais;
	Conta mariaContaSaldoDezReais;
	Conta mariaContaSaldoQuatroReais;
	Dinheiro quatroReais;
	Dinheiro seisReais;
	Dinheiro dezReais;
	Operacao saqueSeisReaisSaldoSuperior;
	Operacao saqueSeisReaisSaldoInferior;
	Operacao depositoDezReais;

	@Before
	public void before() {
		this.sistemaBancario = new SistemaBancario();
		this.bb = this.sistemaBancario.criarBanco(BANCO_DO_BRASIL, Moeda.BRL);
		this.bbCentro = this.bb.criarAgencia(CENTRO);
		this.mariaConta = this.bbCentro.criarConta(MARIA);
		this.dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		this.seisReais = new Dinheiro(Moeda.BRL, 6, 0);
		this.quatroReais = new Dinheiro(Moeda.BRL, 4, 0);

		this.mariaContaDepositadoDezReais = this.bbCentro.criarConta(MARIA);
		this.depositoDezReais = this.sistemaBancario.depositar(this.mariaContaDepositadoDezReais, this.dezReais);

		this.mariaContaSaldoDezReais = this.bbCentro.criarConta(MARIA);
		this.sistemaBancario.depositar(this.mariaContaSaldoDezReais, this.dezReais);
		this.saqueSeisReaisSaldoSuperior = this.sistemaBancario.sacar(this.mariaContaSaldoDezReais, this.seisReais);

		this.mariaContaSaldoQuatroReais = this.bbCentro.criarConta(MARIA);
		this.sistemaBancario.depositar(this.mariaContaSaldoQuatroReais, this.quatroReais);
		this.saqueSeisReaisSaldoInferior = this.sistemaBancario.sacar(this.mariaContaSaldoQuatroReais, this.seisReais);
	}

	// início testes

	@Test
	public void criacaoDeBanco() throws Exception {

		assertEquals(BANCO_DO_BRASIL, this.bb.obterNome());
		assertEquals(Moeda.BRL, this.bb.obterMoeda());
	}

	@Test
	public void criacaoAgencia() throws Exception {

		assertEquals("001", this.bbCentro.obterIdentificador());
		assertEquals(CENTRO, this.bbCentro.obterNome());
		assertEquals(BANCO_DO_BRASIL, this.bbCentro.obterBanco().obterNome());
	}

	@Test
	public void criacaoDeConta() throws Exception {

		assertEquals("0001-5", this.mariaConta.obterIdentificador());
		assertEquals(MARIA, this.mariaConta.obterTitular());
		assertEquals("0,00", this.mariaConta.calcularSaldo().formatado());
		assertEquals(CENTRO, this.mariaConta.obterAgencia().obterNome());
	}

	@Test
	public void operacaoDeposito() throws Exception {

		assertEquals(EstadosDeOperacao.SUCESSO, this.depositoDezReais.obterEstado());
		assertEquals(this.dezReais.toString(), this.mariaContaDepositadoDezReais.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaque() throws Exception {

		assertEquals(EstadosDeOperacao.SUCESSO, this.saqueSeisReaisSaldoSuperior.obterEstado());
		assertEquals(this.quatroReais.toString(), this.mariaContaSaldoDezReais.calcularSaldo().formatarSemSinal());
	}

	@Test
	public void operacaoSaqueInsuficiente() throws Exception {

		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, this.saqueSeisReaisSaldoInferior.obterEstado());
		assertEquals(this.quatroReais.toString(), this.mariaContaSaldoQuatroReais.calcularSaldo().formatarSemSinal());
	}
}
