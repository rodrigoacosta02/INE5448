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
		String nomeFuncionario = "Nome do Funcionario";
		Funcionario funcionario = new Funcionario(nomeFuncionario);
		assertEquals(nomeFuncionario, funcionario.getNome());
		assertEquals(1, funcionario.getId());
	}

}
