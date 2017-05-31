package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Empresa;
import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;

public class TesteEmpresa {

	private Empresa empresa;

	@Before
	public void before() {
		Funcionario.zerarID();
		Projeto.zerarID();
		this.empresa = new Empresa();
	}

	@Test
	public void cadastrarFuncionario() throws Exception {
		String nomeJoao = "João da Silva";
		Funcionario funcionarioJoao = new Funcionario(nomeJoao);
		this.empresa.cadastrarFuncionarios(funcionarioJoao);
		Set<Funcionario> funcionarios = this.empresa.getFuncionarios();

		assertEquals(1, funcionarios.size());
		assertTrue(funcionarios.contains(funcionarioJoao));
	}

	@Test
	public void cadastrarProjeto() throws Exception {
		Projeto projeto = new Projeto();
		this.empresa.cadastrarProjeto(projeto);
		Set<Projeto> projetos = this.empresa.getProjetos();

		assertEquals(1, projetos.size());
		assertTrue(projetos.contains(projeto));
	}

	@Test
	public void cadastrarDoisProjeto() throws Exception {
		this.empresa.cadastrarProjeto(new Projeto());
		this.empresa.cadastrarProjeto(new Projeto());

		assertEquals(2, this.empresa.getProjetos().size());
	}

	@Test
	public void cadastrarDoisFuncionarios() throws Exception {
		this.empresa.cadastrarFuncionarios(new Funcionario("João da Silva"));
		this.empresa.cadastrarFuncionarios(new Funcionario("Maria da Rosa"));

		assertEquals(2, this.empresa.getFuncionarios().size());
	}

	@Test
	public void cadastrarProjetoJaCadastrado() throws Exception {
		Projeto duplo = new Projeto();

		assertTrue(this.empresa.cadastrarProjeto(duplo));
		assertFalse(this.empresa.cadastrarProjeto(duplo));
	}

	@Test
	public void cadastrarFuncionarioJaCadastrado() throws Exception {
		Funcionario funcionario = new Funcionario("João da Silva");

		assertTrue(this.empresa.cadastrarFuncionarios(funcionario));
		assertFalse(this.empresa.cadastrarFuncionarios(funcionario));
	}

}
