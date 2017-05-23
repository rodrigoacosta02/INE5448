package br.inf.ufsc.ocorrencias.entidades;

public class Funcionario {

	private static int ID_ATUAL = 0;
	private int id;
	private String nome;

	public Funcionario(String nome) {
		this.id = ++ID_ATUAL;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

	public static void zerarID() {
		ID_ATUAL = 0;
	}

}
