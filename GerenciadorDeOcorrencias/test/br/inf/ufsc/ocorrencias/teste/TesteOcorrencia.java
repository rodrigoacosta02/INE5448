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

public class TesteOcorrencia {
	Funcionario responsavel;
	static final String resumo = "Resumo da ocorrencia";
	TipoOcorrencia tipo;
	Ocorrencia ocorrencia;

	@Before
	public void before() {
		this.responsavel = new Funcionario("Func Resp");
		this.tipo = TipoOcorrencia.TAREFA;
		this.ocorrencia = new Ocorrencia(this.responsavel, resumo, this.tipo);
	}

	@Test
	public void dadosOcorrencia() throws Exception {

		assertEquals(resumo, this.ocorrencia.getResumo());
		assertEquals(this.responsavel, this.ocorrencia.getResponsavel());
		assertEquals(null, this.ocorrencia.getPrioridade());
		assertEquals(EstadoOcorrencia.ABERTA, this.ocorrencia.getEstado());
		assertEquals(this.tipo, this.ocorrencia.getTipo());
	}

	@Test
	public void mudarPrioridade() throws Exception {
		this.ocorrencia.setPrioridade(PrioridadeOcorrencia.BAIXA);
		this.ocorrencia.setPrioridade(PrioridadeOcorrencia.ALTA);

		assertEquals(PrioridadeOcorrencia.ALTA, this.ocorrencia.getPrioridade());
	}

	@Test
	public void estadoCompletaNaoMudaResponsavelEPrioridade() throws Exception {
		Projeto projeto = new Projeto(this.ocorrencia);
		projeto.concluirOcorrencia(this.ocorrencia);
		Ocorrencia ocorrenciaAtual = projeto.getOcorrencias().iterator().next();
		ocorrenciaAtual.setPrioridade(PrioridadeOcorrencia.ALTA);
		projeto.mudarResponsavelPorOcorrencia(ocorrenciaAtual, new Funcionario("Func 2"));

		assertEquals(null, ocorrenciaAtual.getPrioridade());
		assertEquals(this.responsavel, ocorrenciaAtual.getResponsavel());
	}

}
