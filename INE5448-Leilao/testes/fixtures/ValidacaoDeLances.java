package fixtures;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class ValidacaoDeLances extends ColumnFixture {
	private String nomeProduto;
	private String cpfComprador;
	private Double valorLance;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public ValidacaoDeLances() {
		super();
	}

	public boolean realizarLance() {
		try {
			this.fachada.daLance(this.nomeProduto, this.cpfComprador, this.valorLance);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
