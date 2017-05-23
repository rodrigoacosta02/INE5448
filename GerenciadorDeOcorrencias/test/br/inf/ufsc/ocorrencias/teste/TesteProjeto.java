package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Ocorrencia;
import br.inf.ufsc.ocorrencias.entidades.Projeto;
import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;

public class TesteProjeto {

	final static String resumo = "Resumo da ocorrencia";
	final static String nomeResponsavel = "Nome Responsavel";
	TipoOcorrencia tipo;
	Funcionario responsavel;
	Projeto projeto;
	Ocorrencia ocorrencia;

	@Before
	public void before() {
		this.tipo = TipoOcorrencia.TAREFA;
		this.responsavel = new Funcionario(nomeResponsavel);
		this.projeto = new Projeto();
		this.ocorrencia = new Ocorrencia(this.responsavel, resumo, this.tipo);
		this.projeto.cadastroOcorrencia(this.ocorrencia);
	}

	@Test
	public void cadastrarOcorrencia() throws Exception {

		Set<Ocorrencia> ocorrencias = this.projeto.getOcorrencias();
		Ocorrencia ocorrencia = ocorrencias.iterator().next();
		assertEquals(1, ocorrencias.size());

		assertTrue(ocorrencia.getChaveUnica() > 0);
		assertEquals(resumo, ocorrencia.getResumo());
		assertEquals(this.responsavel, ocorrencia.getResponsavel());
		assertEquals(null, ocorrencia.getPrioridade());
		assertEquals(EstadoOcorrencia.ABERTA, ocorrencia.getEstado());
		assertEquals(this.tipo, ocorrencia.getTipo());
	}

	@Test(expected = RuntimeException.class)
	public void cadastrarOcorrenciaOnzeOcorrenciasMesmoResponsavel() throws Exception {
		for (int i = 0; i < 10; i++) {
			this.projeto.cadastroOcorrencia(new Ocorrencia(this.responsavel, resumo, this.tipo));
		}
	}

	@Test
	public void mudarResponsavelDaOcorrencia() throws Exception {

		Funcionario responsavel2 = new Funcionario("Func Resp2");
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrencia, responsavel2);

		assertEquals(responsavel2, this.ocorrencia.getResponsavel());
	}

	@Test(expected = RuntimeException.class)
	public void mudarResponsavelQuePossuaDezOcorrencias() throws Exception {

		Funcionario responsavel2 = new Funcionario("Func Resp2");
		for (int i = 0; i < 10; i++) {
			this.projeto.cadastroOcorrencia(new Ocorrencia(responsavel2, resumo, this.tipo));
		}
		this.projeto.mudarResponsavelPorOcorrencia(this.ocorrencia, responsavel2);

	}

	@Test
	public void concluirOcorrencia() throws Exception {
		this.projeto.concluirOcorrencia(this.ocorrencia);
		assertEquals(EstadoOcorrencia.COMPLETADA, this.ocorrencia.getEstado());
	}
}
