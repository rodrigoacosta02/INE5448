package fixtures;

import java.text.SimpleDateFormat;
import java.util.List;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;
import modelo.Produto;

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

	@SuppressWarnings("unchecked")
	public boolean verificarProdutoExiste() {
		try {
			List<Produto> produtosEmLeilao = (List<Produto>) this.fachada.getProdutosEmLeilao();
			for (Produto produto : produtosEmLeilao) {
				if (produto.nome().equals(this.nome)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
