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

		assertThat(BANCO_DO_BRASIL, equalTo(this.bb.obterNome()));
		assertThat(Moeda.BRL, equalTo(this.bb.obterMoeda()));
	}

	@Test
	public void criacaoAgencia() throws Exception {

		assertThat("001", equalTo(this.bbCentro.obterIdentificador()));
		assertThat(CENTRO, equalTo(this.bbCentro.obterNome()));
		assertThat(BANCO_DO_BRASIL, equalTo(this.bbCentro.obterBanco().obterNome()));
	}

	@Test
	public void criacaoDeConta() throws Exception {

		assertThat("0001-5", equalTo(this.mariaConta.obterIdentificador()));
		assertThat(MARIA, equalTo(this.mariaConta.obterTitular()));
		assertThat("0,00", equalTo(this.mariaConta.calcularSaldo().formatado()));
		assertThat(CENTRO, equalTo(this.mariaConta.obterAgencia().obterNome()));
	}

	@Test
	public void operacaoDeposito() throws Exception {

		assertThat(EstadosDeOperacao.SUCESSO, equalTo(this.depositoDezReais.obterEstado()));
		assertThat(this.dezReais.toString(),
				equalTo(this.mariaContaDepositadoDezReais.calcularSaldo().formatarSemSinal()));
	}

	@Test
	public void operacaoSaque() throws Exception {

		assertThat(EstadosDeOperacao.SUCESSO, equalTo(this.saqueSeisReaisSaldoSuperior.obterEstado()));
		assertThat(this.quatroReais.toString(),
				equalTo(this.mariaContaSaldoDezReais.calcularSaldo().formatarSemSinal()));
	}

	@Test
	public void operacaoSaqueInsuficiente() throws Exception {

		assertThat(EstadosDeOperacao.SALDO_INSUFICIENTE, equalTo(this.saqueSeisReaisSaldoInferior.obterEstado()));
		assertThat(this.quatroReais.toString(),
				equalTo(this.mariaContaSaldoQuatroReais.calcularSaldo().formatarSemSinal()));
	}
}
