package aula_20170328;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import br.ufsc.ine.leb.sistemaBancario.Conta;

public class CombinadorDeConta extends BaseMatcher<Conta> implements Matcher<Conta> {
	private String identificadorDaConta;
	private String titular;
	private String nomeDoBanco;
	private String nomeDaAgencia;

	public CombinadorDeConta(String identificadorDaConta, String titular, String nomeDoBanco, String nomeDaAgencia) {
		this.identificadorDaConta = identificadorDaConta;
		this.titular = titular;
		this.nomeDoBanco = nomeDoBanco;
		this.nomeDaAgencia = nomeDaAgencia;
	}

	@Override
	public boolean matches(Object object) {
		if (object != null && object instanceof Conta) {
			Conta contaAux = (Conta) object;
			return this.identificadorDaConta.equals(contaAux.obterIdentificador())
					&& this.titular.equals(contaAux.obterTitular())
					&& this.nomeDaAgencia.equals(contaAux.obterAgencia().obterNome())
					&& this.nomeDoBanco.equals(contaAux.obterAgencia().obterBanco().obterNome());
		}
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(" Conta ");
		description.appendValue(this.identificadorDaConta);
		description.appendText(" titular ");
		description.appendValue(this.titular);
		description.appendText(" banco ");
		description.appendValue(this.nomeDoBanco);
		description.appendText(" agencia ");
		description.appendValue(this.nomeDaAgencia);
	}

	@Override
	public void describeMismatch(Object object, Description description) {

		if (object != null && object instanceof Conta) {
			Conta contaAux = (Conta) object;
			description.appendText(" Conta ");
			description.appendValue(contaAux.obterIdentificador());
			description.appendText(" titular ");
			description.appendValue(contaAux.obterTitular());
			description.appendText(" banco ");
			description.appendValue(contaAux.obterAgencia().obterBanco().obterNome());
			description.appendText(" agencia ");
			description.appendValue(contaAux.obterAgencia().obterNome());
		} else {
			super.describeMismatch(object, description);
		}
	}

}
