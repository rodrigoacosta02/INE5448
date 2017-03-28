package aula_20170328;

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
import static org.hamcrest.CoreMatchers.*;
/*
 * Exercício 2 - testes do sistema bancário utilizando os matchers do Hamcrest.
 */

public class TesteAgenciaHamcrest {

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

		assertThat(this.bb.obterNome(), equalTo(BANCO_DO_BRASIL));
		assertThat(this.bb.obterMoeda(), equalTo(Moeda.BRL));
	}

	@Test
	public void criacaoAgencia() throws Exception {

		assertThat(this.bbCentro.obterIdentificador(), equalTo("001"));
		assertThat(this.bbCentro.obterNome(), equalTo(CENTRO));
		assertThat(this.bbCentro.obterBanco().obterNome(), equalTo(BANCO_DO_BRASIL));
	}

	@Test
	public void criacaoDeConta() throws Exception {

		assertThat(this.mariaConta.obterIdentificador(), equalTo("0001-5"));
		assertThat(this.mariaConta.obterTitular(), equalTo(MARIA));
		assertThat(this.mariaConta.calcularSaldo().formatado(), equalTo("0,00"));
		assertThat(this.mariaConta.obterAgencia().obterNome(), equalTo(CENTRO));
	}

	@Test
	public void operacaoDeposito() throws Exception {

		assertThat(this.depositoDezReais.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat(this.mariaContaDepositadoDezReais.calcularSaldo().formatarSemSinal(),
				equalTo(this.dezReais.toString()));
	}

	@Test
	public void operacaoSaque() throws Exception {

		assertThat(this.saqueSeisReaisSaldoSuperior.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat(this.mariaContaSaldoDezReais.calcularSaldo().formatarSemSinal(),
				equalTo(this.quatroReais.toString()));
	}

	@Test
	public void operacaoSaqueInsuficiente() throws Exception {

		assertThat(this.saqueSeisReaisSaldoInferior.obterEstado(), equalTo(EstadosDeOperacao.SALDO_INSUFICIENTE));
		assertThat(this.mariaContaSaldoQuatroReais.calcularSaldo().formatarSemSinal(),
				equalTo(this.quatroReais.toString()));
	}
}
