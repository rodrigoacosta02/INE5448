package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.exceptions.CadastroProjetoException;
import br.inf.ufsc.ocorrencias.geral.Empresa;

public class TesteEmpresa {

	private Empresa empresa;

	@Before
	public void before() {
		Funcionario.zerarID();
		this.empresa = new Empresa();
	}

	@Test
	public void cadastrarFuncionario() throws Exception {
		String funcNome = "Func1";
		this.empresa.cadastrarFuncionarios(new Funcionario(funcNome));
		List<Funcionario> funcionarios = this.empresa.getFuncionarios();

		assertEquals(1, funcionarios.size());
		assertEquals(1, funcionarios.get(0).getId());
		assertEquals(funcNome, funcionarios.get(0).getNome());
	}

	@Test
	public void cadastrarProjeto() throws Exception {
		this.empresa.cadastrarProjeto(new Projeto());
		List<Projeto> projetos = this.empresa.getProjetos();
		assertEquals(1, projetos.size());
	}

	@Test
	public void cadastrarDoisProjeto() throws Exception {
		this.empresa.cadastrarProjeto(new Projeto());
		this.empresa.cadastrarProjeto(new Projeto());
		List<Projeto> todosProjetos = this.empresa.getProjetos();
		assertEquals(2, todosProjetos.size());
	}

	@Test(expected = CadastroProjetoException.class)
	public void cadastrarMesmoProjeto() throws Exception {
		Projeto duplo = new Projeto();
		this.empresa.cadastrarProjeto(duplo);
		this.empresa.cadastrarProjeto(duplo);
	}

	@Test(expected = RuntimeException.class)
	public void cadastrarFuncionarioJaCadastrado() throws Exception {
		String funcNome = "Func1";
		Funcionario funcionario = new Funcionario(funcNome);
		this.empresa.cadastrarFuncionarios(funcionario);
		this.empresa.cadastrarFuncionarios(funcionario);
	}

}
