package br.inf.ufsc.ocorrencias.entidades;

public class Funcionario {
	private int id;
	private String nome;

	public Funcionario(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

}
