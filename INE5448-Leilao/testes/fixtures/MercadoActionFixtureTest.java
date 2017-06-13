package fixtures;

import fit.ActionFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class MercadoActionFixtureTest extends ActionFixture {

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public void montarMercado() {
		this.fachada.montarMercado();
	}

	public Boolean mercadoMontado() {
		return this.fachada.isMercadoMontado();
	}

	public void desmontarMercado() {
		this.fachada.desmontarMercado();
	}

	public Boolean mercadoDesmontado() {
		return this.fachada.isMercadoDesmontado();
	}

	public void limparMercado() {
		this.fachada.limparMercado();
	}
}
