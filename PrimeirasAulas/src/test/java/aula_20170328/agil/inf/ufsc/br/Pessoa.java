package aula_20170328.agil.inf.ufsc.br;

public class Pessoa {

	private Integer identificador;
	private String nome;
	private Integer idade;
	private Ocupacao ocupacao;

	public Pessoa(Integer identificador, String nome, Integer idade, Ocupacao ocupacao) {
		this.identificador = identificador;
		this.nome = nome;
		this.idade = idade;
		this.ocupacao = ocupacao;
	}

	@Override
	public boolean equals(Object objeto) {
		System.out.println(nome);
		if (objeto instanceof Pessoa) {
			Pessoa outra = (Pessoa) objeto;
			return identificador == outra.identificador;
		}
		return super.equals(objeto);
	}

	public String obterNome() {
		return nome;
	}

	public Integer obterIdade() {
		return idade;
	}

	public Ocupacao obterOcupacao() {
		return ocupacao;
	}

	public Boolean maiorDeIdade() {
		return idade >= 18;
	}
}
