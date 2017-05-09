package aula_20170411;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

@FixtureSetup(Test03ContaEstoria.class)
public class Test04OperacoesEstoria {

	@Fixture
	private SistemaBancario sistemaBancario;
	@Fixture
	private Banco bancoDoBrasil;
	@Fixture
	private Agencia bancoDoBrasilCentro;
	@Fixture
	private Conta mariaConta;

	@Test
	public void operacaoDeposito() throws Exception {
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		Operacao deposito = sistemaBancario.depositar(mariaConta, dezReais);
		assertEquals(EstadosDeOperacao.SUCESSO, deposito.obterEstado());
		assertEquals(dezReais.toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}
	
	@Test
	public void operacaoSaque() throws Exception {
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, 10, 0);
		sistemaBancario.depositar(mariaConta, dezReais);
		Operacao operacao = sistemaBancario.sacar(mariaConta, new Dinheiro(Moeda.BRL, 6, 0));

		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(new Dinheiro(Moeda.BRL, 4, 0).toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}
	@Test
	public void operacaoSaqueInsuficiente() throws Exception {
		Dinheiro quatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		sistemaBancario.depositar(mariaConta, quatroReais);
		Operacao operacao = sistemaBancario.sacar(mariaConta, new Dinheiro(Moeda.BRL, 6, 0));

		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, operacao.obterEstado());
		assertEquals(quatroReais.toString(), mariaConta.calcularSaldo().formatarSemSinal());
	}
}