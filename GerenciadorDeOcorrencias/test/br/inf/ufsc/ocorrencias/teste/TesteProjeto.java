package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Ocorrencia;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.PrioridadeOcorrencia;
import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;
import br.inf.ufsc.ocorrencias.exceptions.CadastroOcorrenciaException;

public class TesteProjeto {

	private final static String resumo = "Resumo da ocorrencia";
	private final static String nomeResponsavel = "João da Silva";
	private Funcionario responsavelJoao;
	private Projeto projeto;
	private Ocorrencia ocorrenciaTarefa;

	@Before
	public void before() {
		Funcionario.zerarID();
		Ocorrencia.zerarChaveUnica();
		Projeto.zerarID();

		this.responsavelJoao = new Funcionario(nomeResponsavel);
		this.ocorrenciaTarefa = new Ocorrencia(this.responsavelJoao, resumo, TipoOcorrencia.TAREFA);
		this.projeto = new Projeto();
	}

	@Test
	public void novoProjeto() throws Exception {
		assertEquals(1, this.projeto.getIdProjeto());
		assertNotNull(this.projeto.getOcorrencias());
		assertNotNull(this.projeto.getLimiteResponsaveis());
	}

	@Test
	public void doisProjetos() throws Exception {
		Projeto segundoProjeto = new Projeto();

		assertEquals(1, this.projeto.getIdProjeto());
		assertEquals(2, segundoProjeto.getIdProjeto());
	}

	@Test
	public void cadastrarOcorrencia() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);

		assertEquals(1, this.projeto.getOcorrencias().size());
		assertTrue(this.projeto.getOcorrencias().contains(this.ocorrenciaTarefa));
	}

	@Test
	public void cadastrarDuasOcorrencia() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);

		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		String resumoOcorrenciaBug = "Resumo 2";
		Ocorrencia ocorrenciaBug = new Ocorrencia(responsavelMaria, resumoOcorrenciaBug, TipoOcorrencia.BUG);
		this.projeto.cadastroOcorrencia(ocorrenciaBug);

		assertEquals(2, this.projeto.getOcorrencias().size());
		assertTrue(this.projeto.getOcorrencias().contains(this.ocorrenciaTarefa));
		assertTrue(this.projeto.getOcorrencias().contains(ocorrenciaBug));

	}

	@Test
	public void cadastrarMesmaOcorrencia() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);

		assertEquals(1, this.projeto.getOcorrencias().size());
		assertTrue(this.projeto.getOcorrencias().contains(this.ocorrenciaTarefa));

	}

	@Test
	public void cadastraDezOcorrenciasParaMesmoResponsavel() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		for (int i = 0; i < 9; i++) {
			this.projeto.cadastroOcorrencia(new Ocorrencia(this.responsavelJoao, resumo, TipoOcorrencia.TAREFA));
		}

		assertEquals(10, this.projeto.getOcorrencias().size());
		assertEquals(1, this.projeto.getLimiteResponsaveis().size());
		assertEquals(10, this.projeto.getLimiteResponsaveis().get(this.responsavelJoao).intValue());

	}

	@Test(expected = CadastroOcorrenciaException.class)
	public void cadastraOnzeOcorrenciasParaMesmoResponsavel() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		this.cadastrarDezOcorrencias(this.responsavelJoao);
	}

	@Test(expected = CadastroOcorrenciaException.class)
	public void mudarResponsavelDaOcorrenciaSemOcorrenciaCadastrada() throws Exception {
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrenciaTarefa, responsavelMaria);
	}

	@Test
	public void mudarResponsavelDaOcorrencia() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrenciaTarefa, responsavelMaria);

		assertEquals(responsavelMaria, this.projeto.getOcorrenciaDaLista(this.ocorrenciaTarefa).getResponsavel());
	}

	@Test(expected = CadastroOcorrenciaException.class)
	public void mudarResponsavelQuePossuaDezOcorrencias() throws Exception {
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.cadastrarDezOcorrencias(responsavelMaria);
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrenciaTarefa, responsavelMaria);
	}

	@Test
	public void concluirOcorrencia() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		this.projeto.concluirOcorrencia(this.ocorrenciaTarefa);

		assertEquals(EstadoOcorrencia.COMPLETADA, this.projeto.getOcorrenciaDaLista(this.ocorrenciaTarefa).getEstado());
	}

	@Test(expected = CadastroOcorrenciaException.class)
	public void concluirOcorrenciaNaoCadastrada() throws Exception {
		this.projeto.concluirOcorrencia(this.ocorrenciaTarefa);
	}

	@Test(expected = CadastroOcorrenciaException.class)
	public void retornarOcorrenciaNaoCadastrada() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		Ocorrencia ocorrenciaForaDaLista = new Ocorrencia(responsavelMaria, "Resumo", TipoOcorrencia.TAREFA);
		this.projeto.getOcorrenciaDaLista(ocorrenciaForaDaLista);
	}

	@Test
	public void estadoCompletaNaoMudaResponsavel() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		this.projeto.concluirOcorrencia(this.ocorrenciaTarefa);

		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrenciaTarefa, responsavelMaria);

		assertEquals(this.responsavelJoao, this.projeto.getOcorrenciaDaLista(this.ocorrenciaTarefa).getResponsavel());
	}

	@Test
	public void estadoCompletaNaoMudaPrioridade() throws Exception {
		this.projeto.cadastroOcorrencia(this.ocorrenciaTarefa);
		this.projeto.concluirOcorrencia(this.ocorrenciaTarefa);

		this.projeto.getOcorrenciaDaLista(this.ocorrenciaTarefa).setPrioridade(PrioridadeOcorrencia.ALTA);

		PrioridadeOcorrencia prioridade = this.projeto.getOcorrenciaDaLista(this.ocorrenciaTarefa).getPrioridade();
		assertEquals(null, prioridade);
		assertNotEquals(PrioridadeOcorrencia.ALTA, prioridade);

	}

	/*
	 * Métodos auxiliares
	 */

	private void cadastrarDezOcorrencias(Funcionario responsavel) {
		for (int i = 0; i < 10; i++) {
			String resumoAleatorio = "Resumo " + 1;
			this.projeto.cadastroOcorrencia(new Ocorrencia(responsavel, resumoAleatorio, TipoOcorrencia.TAREFA));
		}
	}

}
