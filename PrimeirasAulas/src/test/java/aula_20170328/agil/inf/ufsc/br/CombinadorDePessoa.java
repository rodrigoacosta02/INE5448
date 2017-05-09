package aula_20170328.agil.inf.ufsc.br;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CombinadorDePessoa extends BaseMatcher<Pessoa> implements Matcher<Pessoa> {
	private String nome;
	private Integer idade;
	private Ocupacao ocupacao;

	public CombinadorDePessoa(String nome, Integer idade, Ocupacao ocupacao) {
		this.nome = nome;
		this.idade = idade;
		this.ocupacao = ocupacao;
	}

	public boolean matches(Object objeto) {
		if (objeto != null && objeto instanceof Pessoa) {
			Pessoa outra = (Pessoa) objeto;
			return this.nome.equals(outra.obterNome()) && this.idade.equals(outra.obterIdade())
					&& this.ocupacao.equals(outra.obterOcupacao());
		}
		return false;
	}

	public void describeTo(Description descricao) {
		descricao.appendValue(this.ocupacao);
		descricao.appendText(" de nome ");
		descricao.appendValue(this.nome);
		descricao.appendText(" e ");
		descricao.appendValue(this.idade);
		descricao.appendText(" anos");
	}

	@Override
	public void describeMismatch(Object objeto, Description descricao) {
		if (objeto != null && objeto instanceof Pessoa) {
			Pessoa outra = (Pessoa) objeto;
			descricao.appendValue(outra.obterOcupacao());
			descricao.appendText(" de nome ");
			descricao.appendValue(outra.obterNome());
			descricao.appendText(" e ");
			descricao.appendValue(outra.obterIdade());
			descricao.appendText(" anos");
		} else {
			super.describeMismatch(objeto, descricao);
		}
	}
}
