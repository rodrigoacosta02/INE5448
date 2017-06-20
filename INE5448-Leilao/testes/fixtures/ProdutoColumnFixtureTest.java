package fixtures;

import java.text.SimpleDateFormat;
import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class ProdutoColumnFixtureTest extends ColumnFixture {
	private String nome;
	private String descricao;
	private String dataLimite;
	private String cpfLeiloador;
	private Double lanceMinimo;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public ProdutoColumnFixtureTest() {
		super();
	}

	public boolean cadastrarProduto() {
		try {
			this.fachada.cadastrarProduto(this.nome, this.descricao, this.lanceMinimo, this.cpfLeiloador,
					new SimpleDateFormat("yyyy-MM-dd").parse(this.dataLimite));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificarProdutoExiste() {
		return this.fachada.verificaSeOProdutoJaExiste(this.nome);
	}
}
