package fixtures;

import java.util.List;

import fit.ColumnFixture;
import interfaces.ILeiloavel;
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

	public boolean verificarLanceUsuario() {
		try {
			List<? extends ILeiloavel> produtosQueDeuLance = this.fachada.getProdutosQueDeuLance(this.cpfComprador);
			for (ILeiloavel iLeiloavel : produtosQueDeuLance) {
				if (iLeiloavel.getNome().equals(this.nomeProduto)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
