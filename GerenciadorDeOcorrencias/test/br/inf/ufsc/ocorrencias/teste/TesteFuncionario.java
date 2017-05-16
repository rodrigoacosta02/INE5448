package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;

public class TesteFuncionario {

	@Test
	public void dadosFuncionario() throws Exception {
		String nomeFuncionario = "Nome do Funcionario";
		int idFunc = 1;
		Funcionario funcionario = new Funcionario(idFunc, nomeFuncionario);
		assertEquals(nomeFuncionario, funcionario.getNome());
		assertEquals(idFunc, funcionario.getId());
	}
}
