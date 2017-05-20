package br.inf.ufsc.ocorrencias.entidades;

import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.PrioridadeOcorrencia;
import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;

public class Ocorrencia {

	private static int CHAVE_ATUAL = 0;
	private int chaveUnica;
	private String resumo;
	private Funcionario responsavel;
	private PrioridadeOcorrencia prioridade;
	private TipoOcorrencia tipo;
	private EstadoOcorrencia estado;

	public Ocorrencia(Funcionario responsavel, String resumo, TipoOcorrencia tipo) {
		this.chaveUnica = ++CHAVE_ATUAL;
		this.estado = EstadoOcorrencia.ABERTA;
		this.responsavel = responsavel;
		this.resumo = resumo;
		this.tipo = tipo;
	}

	public int getChaveUnica() {
		return this.chaveUnica;
	}

	public String getResumo() {
		return this.resumo;
	}

	public Funcionario getResponsavel() {
		return this.responsavel;
	}

	public PrioridadeOcorrencia getPrioridade() {
		return this.prioridade;
	}

	public TipoOcorrencia getTipo() {
		return this.tipo;
	}

	public EstadoOcorrencia getEstado() {
		return this.estado;
	}

	protected void setResponsavel(Funcionario responsavel) {
		if (this.estado != EstadoOcorrencia.COMPLETADA) {
			this.responsavel = responsavel;
		}
	}

	public void setPrioridade(PrioridadeOcorrencia prioridade) {
		if (this.estado != EstadoOcorrencia.COMPLETADA) {
			this.prioridade = prioridade;
		}
	}

	protected void setEstado(EstadoOcorrencia estado) {
		this.estado = estado;
	}

}
