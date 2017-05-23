package br.inf.ufsc.ocorrencias.geral;

import java.util.LinkedList;
import java.util.List;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.exceptions.CadastroFuncionarioException;
import br.inf.ufsc.ocorrencias.exceptions.CadastroProjetoException;

public class Empresa {

	private List<Funcionario> funcionarios;
	private List<Projeto> projetos;

	public Empresa() {
		this.funcionarios = new LinkedList<Funcionario>();
		this.projetos = new LinkedList<Projeto>();
	}

	public void cadastrarFuncionarios(Funcionario funcionario) {
		if (this.funcionarios.contains(funcionario)) {
			throw new CadastroFuncionarioException("Funcionario j� cadastrado");
		}
		this.funcionarios.add(funcionario);
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public void cadastrarProjeto(Projeto projeto) {
		if (this.projetos.contains(projeto)) {
			throw new CadastroProjetoException("Projeto j� cadastrado");
		}
		this.projetos.add(projeto);
	}

	public List<Projeto> getProjetos() {
		return this.projetos;
	}

}
