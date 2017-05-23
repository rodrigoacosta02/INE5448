package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.geral.Empresa;

public class TesteEmpresa {

	private Empresa empresa;

	@Before
	public void before() {
		this.empresa = new Empresa();
	}

	@Test
	public void cadastrarFuncionario() throws Exception {
		String funcNome = "Func1";
		this.empresa.cadastrarFuncionarios(new Funcionario(funcNome));
		List<Funcionario> funcionarios = this.empresa.getFuncionarios();

		assertEquals(1, funcionarios.size());
		assertTrue(funcionarios.get(0).getId() > 0);
		assertEquals(funcNome, funcionarios.get(0).getNome());
	}

	@Test
	public void cadastrarProjeto() throws Exception {
		this.empresa.cadastrarProjeto(new Projeto());
		List<Projeto> projetos = this.empresa.getProjetos();
		assertEquals(1, projetos.size());
	}

	@Test(expected = RuntimeException.class)
	public void cadastrarFuncionarioJaCadastrado() throws Exception {
		String funcNome = "Func1";
		Funcionario funcionario = new Funcionario(funcNome);
		this.empresa.cadastrarFuncionarios(funcionario);
		this.empresa.cadastrarFuncionarios(funcionario);
	}

}
