package fixtures;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class UsuarioColumnFixtureTest extends ColumnFixture {
	public String nome;
	public String endereco;
	public String email;
	public String cpf;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	public UsuarioColumnFixtureTest() {
		super();
	}

	public boolean cadastrarUsuario() {
		try {
			this.fachada.cadastrarUsuario(this.nome, this.endereco, this.email, this.cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificarUsuarioExiste() {
		try {
			if (this.fachada.getUsuarioPor(this.cpf) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}