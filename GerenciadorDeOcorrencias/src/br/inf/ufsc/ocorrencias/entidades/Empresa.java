package br.inf.ufsc.ocorrencias.entidades;

import java.util.HashSet;
import java.util.Set;

public class Empresa {

	private Set<Funcionario> funcionarios;
	private Set<Projeto> projetos;

	public Empresa() {
		this.funcionarios = new HashSet<Funcionario>();
		this.projetos = new HashSet<Projeto>();
	}

	public boolean cadastrarFuncionarios(Funcionario funcionario) {
		return this.funcionarios.add(funcionario);
	}

	public Set<Funcionario> getFuncionarios() {
		return this.funcionarios;
	}

	public boolean cadastrarProjeto(Projeto projeto) {
		return this.projetos.add(projeto);
	}

	public Set<Projeto> getProjetos() {
		return this.projetos;
	}
}
