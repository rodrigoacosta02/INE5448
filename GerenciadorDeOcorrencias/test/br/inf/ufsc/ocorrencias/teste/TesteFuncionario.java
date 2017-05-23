package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;

public class TesteFuncionario {

	@Test
	public void dadosFuncionario() throws Exception {
		String nomeFuncionario = "Nome do Funcionario";
		Funcionario funcionario = new Funcionario(nomeFuncionario);
		assertEquals(nomeFuncionario, funcionario.getNome());
		assertTrue(funcionario.getId() > 0);
	}

}
