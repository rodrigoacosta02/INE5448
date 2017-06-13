package fixtures;

import java.util.Date;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class ProdutoColumnFixtureTest extends ColumnFixture {
	public String nome;
	public String usuarioAssociado;
	String descricao;
	private Date dataLimite;
	private String cpfLeiloador;
	private Double lanceMinimo;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public ProdutoColumnFixtureTest() {
		super();
	}

	public boolean cadastrarProduto() {
		try {
			this.fachada.cadastrarProduto(this.nome, this.descricao, this.lanceMinimo, this.cpfLeiloador, this.dataLimite);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
