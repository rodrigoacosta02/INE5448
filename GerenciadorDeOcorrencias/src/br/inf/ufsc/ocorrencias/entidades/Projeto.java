package br.inf.ufsc.ocorrencias.entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;

public class Projeto {

	private Set<Ocorrencia> ocorrencias;
	private Map<Funcionario, Integer> limiteResponsaveis;

	public Projeto() {
		this.ocorrencias = new HashSet<Ocorrencia>();
		this.limiteResponsaveis = new HashMap<Funcionario, Integer>();
	}

	public Set<Ocorrencia> getOcorrencias() {
		return this.ocorrencias;
	}

	public void cadastroOcorrencia(Ocorrencia ocorrencia) {
		this.validarLimiteResponsavelPorOcorrencia(ocorrencia.getResponsavel());
		this.ocorrencias.add(ocorrencia);
	}

	public void mudarResponsavelPorOcorrencia(Ocorrencia ocorrencia, Funcionario responsavel) {
		this.validarLimiteResponsavelPorOcorrencia(responsavel);

		this.getOcorrenciaDaLista(ocorrencia).setResponsavel(responsavel);
	}

	private void validarLimiteResponsavelPorOcorrencia(Funcionario responsavel) {
		if (this.limiteResponsaveis.containsKey(responsavel)) {
			int numDeOcorrenciasDoResponsavel = this.limiteResponsaveis.get(responsavel);
			if (numDeOcorrenciasDoResponsavel == 10) {
				throw new RuntimeException("Limite de ocorrencias por responsavel");
			}
			this.limiteResponsaveis.put(responsavel, numDeOcorrenciasDoResponsavel + 1);
		} else {
			this.limiteResponsaveis.put(responsavel, 1);
		}
	}

	private Ocorrencia getOcorrenciaDaLista(Ocorrencia ocorrencia) {
		for (Ocorrencia ocorrenciaValor : this.ocorrencias) {
			if (ocorrenciaValor.equals(ocorrencia)) {
				return ocorrenciaValor;
			}
		}
		throw new RuntimeException("Ocorrencia não cadastrada");
	}

	public void concluirOcorrencia(Ocorrencia ocorrencia) {
		this.getOcorrenciaDaLista(ocorrencia).setEstado(EstadoOcorrencia.COMPLETADA);
	}

}
