package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.geral.Empresa;

public class TesteEmpresa {

	@Test
	public void cadastrarFuncionario() throws Exception {
		String funcNome = "Func1";

		Empresa empresa = new Empresa();
		empresa.cadastrarFuncionarios(new Funcionario(funcNome));

		List<Funcionario> funcionarios = empresa.getFuncionarios();

		assertEquals(1, funcionarios.size());
		assertTrue(funcionarios.get(0).getId() > 0);
		assertEquals(funcNome, funcionarios.get(0).getNome());
	}

	@Test
	public void cadastrarProjeto() throws Exception {
		Empresa empresa = new Empresa();
		empresa.cadastrarProjeto(new Projeto());
		List<Projeto> projetos = empresa.getProjetos();
		assertEquals(1, projetos.size());
	}

}
