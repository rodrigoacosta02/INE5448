package br.ufsc.ine.leb.sistemaBancario;

public class Entrada implements Transacao {

	private Dinheiro quantia;

	public Entrada(Conta conta, Dinheiro quantia) {
		this.quantia = quantia;
	}

	public ValorMonetario obterValorMonetario() {
		return quantia.positivo();
	}

	public ValorMonetario contabilizar(ValorMonetario saldo) {
		return saldo.somar(quantia);
	}

}
