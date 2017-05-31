package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import br.inf.ufsc.ocorrencias.entidades.Funcionario;
import br.inf.ufsc.ocorrencias.entidades.Ocorrencia;
import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.PrioridadeOcorrencia;
import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;

public class TesteOcorrencia {
	private static final String nomeDoResponsavel = "João da Silva";
	private static final String resumo = "Resumo da ocorrencia";

	private Ocorrencia ocorrencia;
	private TipoOcorrencia tipo;
	private Funcionario responsavelJoao;

	@Before
	public void before() {
		Funcionario.zerarID();
		Ocorrencia.zerarChaveUnica();

		this.responsavelJoao = new Funcionario(nomeDoResponsavel);
		this.tipo = TipoOcorrencia.TAREFA;
		this.ocorrencia = new Ocorrencia(this.responsavelJoao, resumo, this.tipo);
	}

	@Test
	public void dadosOcorrencia() throws Exception {

		assertEquals(1, this.ocorrencia.getChaveUnica());
		assertEquals(resumo, this.ocorrencia.getResumo());
		assertEquals(this.responsavelJoao, this.ocorrencia.getResponsavel());
		assertEquals(null, this.ocorrencia.getPrioridade());
		assertEquals(EstadoOcorrencia.ABERTA, this.ocorrencia.getEstado());
		assertEquals(this.tipo, this.ocorrencia.getTipo());
	}

	@Test
	public void duasOcorrencias() throws Exception {
		Funcionario responsavel2 = new Funcionario("Maria da Rosa");
		Ocorrencia ocorrencia2 = new Ocorrencia(responsavel2, "Outro resumo", TipoOcorrencia.BUG);

		assertEquals(1, this.ocorrencia.getChaveUnica());
		assertEquals(2, ocorrencia2.getChaveUnica());
	}

	@Test
	public void mudarPrioridade() throws Exception {
		for (PrioridadeOcorrencia prioridade : PrioridadeOcorrencia.values()) {
			this.ocorrencia.setPrioridade(prioridade);
			assertEquals(prioridade, this.ocorrencia.getPrioridade());
		}
	}

	@Test
	public void naoMudarPrioridadeAposEstadoCompletada() throws Exception {
		this.ocorrencia.setPrioridade(PrioridadeOcorrencia.ALTA);
		this.alteracaoDeEstadoParaCompletada(this.ocorrencia);
		this.ocorrencia.setPrioridade(PrioridadeOcorrencia.BAIXA);

		assertEquals(PrioridadeOcorrencia.ALTA, this.ocorrencia.getPrioridade());
	}

	@Test
	public void mudarResponsavel() throws Exception {
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.setResponsavel(this.ocorrencia, responsavelMaria);
		assertEquals(responsavelMaria, this.ocorrencia.getResponsavel());
	}

	@Test
	public void naoMudarResponsavelAposEstadoCompletada() throws Exception {
		Funcionario responsavelMaria = new Funcionario("Maria da Rosa");
		this.alteracaoDeEstadoParaCompletada(this.ocorrencia);
		this.setResponsavel(this.ocorrencia, responsavelMaria);
		assertEquals(this.responsavelJoao, this.ocorrencia.getResponsavel());
	}

	@Test
	public void mudarEstadoCompletada() throws Exception {
		this.setEstado(this.ocorrencia, EstadoOcorrencia.COMPLETADA);
		assertEquals(EstadoOcorrencia.COMPLETADA, this.ocorrencia.getEstado());
	}

	@Test
	public void mudarEstadoAberta() throws Exception {
		this.setEstado(this.ocorrencia, EstadoOcorrencia.ABERTA);
		assertEquals(EstadoOcorrencia.ABERTA, this.ocorrencia.getEstado());
	}

	@Test
	public void naoMudarEstadoAposCompletada() throws Exception {
		this.setEstado(this.ocorrencia, EstadoOcorrencia.COMPLETADA);
		this.setEstado(this.ocorrencia, EstadoOcorrencia.ABERTA);
		assertEquals(EstadoOcorrencia.COMPLETADA, this.ocorrencia.getEstado());
	}

	/*
	 * métodos auxiliares
	 */

	private void alteracaoDeEstadoParaCompletada(Ocorrencia ocorrencia) throws Exception {
		Field privateField = Ocorrencia.class.getDeclaredField("estado");
		privateField.setAccessible(true);
		privateField.set(ocorrencia, EstadoOcorrencia.COMPLETADA);
	}

	private void setResponsavel(Ocorrencia ocorrencia, Funcionario responsavel) throws Exception {
		Method privateMethod = Ocorrencia.class.getDeclaredMethod("setResponsavel", Funcionario.class);
		privateMethod.setAccessible(true);
		privateMethod.invoke(ocorrencia, responsavel);
	}

	private void setEstado(Ocorrencia ocorrencia, EstadoOcorrencia estado) throws Exception {
		Method privateMethod = Ocorrencia.class.getDeclaredMethod("setEstado", EstadoOcorrencia.class);
		privateMethod.setAccessible(true);
		privateMethod.invoke(ocorrencia, estado);
	}
}
