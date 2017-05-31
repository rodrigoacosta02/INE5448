package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;

public class TesteFuncionario {

	@Before
	public void before() {
		Funcionario.zerarID();
	}

	@Test
	public void dadosFuncionario() throws Exception {
		String nomeFuncionario = "João da Silva";
		Funcionario funcionario = new Funcionario(nomeFuncionario);
		assertEquals(nomeFuncionario, funcionario.getNome());
		assertEquals(1, funcionario.getId());
	}

	@Test
	public void doisFuncionarios() throws Exception {
		String nomeFuncionario = "João da Silva";
		Funcionario funcionario = new Funcionario(nomeFuncionario);
		String nomeFunc2 = "Maria da Rosa";
		Funcionario func2 = new Funcionario(nomeFunc2);

		assertEquals(nomeFuncionario, funcionario.getNome());
		assertEquals(1, funcionario.getId());
		assertEquals(nomeFunc2, func2.getNome());
		assertEquals(2, func2.getId());
	}

	@Test
	public void doisFuncionariosMesmoNome() throws Exception {
		String nomeFuncionario = "João da Silva";
		Funcionario funcionario = new Funcionario(nomeFuncionario);
		Funcionario func2 = new Funcionario(nomeFuncionario);

		assertEquals(nomeFuncionario, funcionario.getNome());
		assertEquals(1, funcionario.getId());
		assertEquals(nomeFuncionario, func2.getNome());
		assertEquals(2, func2.getId());
	}

}
