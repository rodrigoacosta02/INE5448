package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Ocorrencia;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.PrioridadeOcorrencia;
import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;

public class TesteProjeto {

	final static String resumo = "Resumo da ocorrencia";
	final static String nomeResponsavel = "Nome Responsavel";
	TipoOcorrencia tipoOcorrencia;
	Funcionario responsavelOne;
	Projeto projetoOne;
	Ocorrencia ocorrenciaOne;

	@Before
	public void before() {
		Funcionario.zerarID();
		Ocorrencia.zerarChaveUnica();

		this.tipoOcorrencia = TipoOcorrencia.TAREFA;
		this.responsavelOne = new Funcionario(nomeResponsavel);
		this.projetoOne = new Projeto();
		this.ocorrenciaOne = new Ocorrencia(this.responsavelOne, resumo, this.tipoOcorrencia);
		this.projetoOne.cadastroOcorrencia(this.ocorrenciaOne);
	}

	@Test
	public void cadastrarOcorrencia() throws Exception {
		Set<Ocorrencia> ocorrencias = this.projetoOne.getOcorrencias();
		Ocorrencia ocorrencia = ocorrencias.iterator().next();
		assertEquals(1, ocorrencias.size());

		assertEquals(1, ocorrencia.getChaveUnica());
		assertEquals(resumo, ocorrencia.getResumo());
		assertEquals(this.responsavelOne, ocorrencia.getResponsavel());
		assertEquals(null, ocorrencia.getPrioridade());
		assertEquals(EstadoOcorrencia.ABERTA, ocorrencia.getEstado());
		assertEquals(this.tipoOcorrencia, ocorrencia.getTipo());
	}

	@Test(expected = RuntimeException.class)
	public void cadastrarOcorrenciaOnzeOcorrenciasMesmoResponsavel() throws Exception {
		for (int i = 0; i < 10; i++) {
			this.projetoOne.cadastroOcorrencia(new Ocorrencia(this.responsavelOne, resumo, this.tipoOcorrencia));
		}
	}

	@Test
	public void mudarResponsavelDaOcorrencia() throws Exception {

		Funcionario responsavel2 = new Funcionario("Func Resp2");
		this.projetoOne.mudarResponsavelPorOcorrencia(this.ocorrenciaOne, responsavel2);

		assertEquals(responsavel2, this.ocorrenciaOne.getResponsavel());
	}

	@Test(expected = RuntimeException.class)
	public void mudarResponsavelQuePossuaDezOcorrencias() throws Exception {

		Funcionario responsavel2 = new Funcionario("Func Resp2");
		for (int i = 0; i < 10; i++) {
			this.projetoOne.cadastroOcorrencia(new Ocorrencia(responsavel2, resumo, this.tipoOcorrencia));
		}
		this.projetoOne.mudarResponsavelPorOcorrencia(this.ocorrenciaOne, responsavel2);
	}

	@Test
	public void concluirOcorrencia() throws Exception {
		this.projetoOne.concluirOcorrencia(this.ocorrenciaOne);
		assertEquals(EstadoOcorrencia.COMPLETADA, this.ocorrenciaOne.getEstado());
	}

	@Test(expected = RuntimeException.class)
	public void concluirOcorrenciaNaoIniciadoNaListaDoProjeto() throws Exception {
		this.projetoOne.concluirOcorrencia(new Ocorrencia(new Funcionario("Teste Func"), "", TipoOcorrencia.MELHORIA));
	}

	@Test
	public void estadoCompletaNaoMudaResponsavelEPrioridade() throws Exception {
		Projeto projeto = new Projeto();
		projeto.cadastroOcorrencia(this.ocorrenciaOne);
		projeto.concluirOcorrencia(this.ocorrenciaOne);
		Ocorrencia ocorrenciaAtual = projeto.getOcorrencias().iterator().next();
		ocorrenciaAtual.setPrioridade(PrioridadeOcorrencia.ALTA);
		projeto.mudarResponsavelPorOcorrencia(ocorrenciaAtual, new Funcionario("Func 2"));

		assertEquals(null, ocorrenciaAtual.getPrioridade());
		assertEquals(this.responsavelOne, ocorrenciaAtual.getResponsavel());
	}

}
