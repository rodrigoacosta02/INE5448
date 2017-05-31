package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Empresa;
import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.exceptions.CadastroProjetoException;

public class TesteEmpresa {

	private Empresa empresa;

	@Before
	public void before() {
		Funcionario.zerarID();
		this.empresa = new Empresa();
	}

	@Test
	public void cadastrarFuncionario() throws Exception {
		String nomeJoao = "João da Silva";
		this.empresa.cadastrarFuncionarios(new Funcionario(nomeJoao));
		List<Funcionario> funcionarios = this.empresa.getFuncionarios();

		assertEquals(1, funcionarios.size());
		assertEquals(1, funcionarios.get(0).getId());
		assertEquals(nomeJoao, funcionarios.get(0).getNome());
	}

	@Test
	public void cadastrarProjeto() throws Exception {
		this.empresa.cadastrarProjeto(new Projeto());
		assertEquals(1, this.empresa.getProjetos().size());
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

	@Test(expected = CadastroProjetoException.class)
	public void cadastrarMesmoProjeto() throws Exception {
		Projeto duplo = new Projeto();
		this.empresa.cadastrarProjeto(duplo);
		this.empresa.cadastrarProjeto(duplo);
	}

	@Test(expected = RuntimeException.class)
	public void cadastrarFuncionarioJaCadastrado() throws Exception {
		Funcionario funcionario = new Funcionario("João da Silva");
		this.empresa.cadastrarFuncionarios(funcionario);
		this.empresa.cadastrarFuncionarios(funcionario);
	}

}
