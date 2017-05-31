package br.inf.ufsc.ocorrencias.entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.exceptions.CadastroOcorrenciaException;

public class Projeto {
	private static int ID_ATUAL = 0;

	private int idProjeto;
	private Set<Ocorrencia> ocorrencias;
	private Map<Funcionario, Integer> limiteResponsaveis;

	public Projeto() {
		this.idProjeto = ++ID_ATUAL;
		this.ocorrencias = new HashSet<Ocorrencia>();
		this.limiteResponsaveis = new HashMap<Funcionario, Integer>();
	}

	public int getIdProjeto() {
		return this.idProjeto;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return this.ocorrencias;
	}

	public Map<Funcionario, Integer> getLimiteResponsaveis() {
		return this.limiteResponsaveis;
	}

	public boolean cadastroOcorrencia(Ocorrencia ocorrencia) {
		this.validarLimiteResponsavelPorOcorrencia(ocorrencia.getResponsavel());
		return this.ocorrencias.add(ocorrencia);
	}

	public void mudarResponsavelPorOcorrencia(Ocorrencia ocorrencia, Funcionario responsavel) {
		this.validarLimiteResponsavelPorOcorrencia(responsavel);

		this.getOcorrenciaDaLista(ocorrencia).setResponsavel(responsavel);
	}

	private void validarLimiteResponsavelPorOcorrencia(Funcionario responsavel) {
		if (this.limiteResponsaveis.containsKey(responsavel)) {
			int numDeOcorrenciasDoResponsavel = this.limiteResponsaveis.get(responsavel);
			if (numDeOcorrenciasDoResponsavel == 10) {
				throw new CadastroOcorrenciaException("Limite de ocorrencias por responsavel");
			}
			this.limiteResponsaveis.put(responsavel, numDeOcorrenciasDoResponsavel + 1);
		} else {
			this.limiteResponsaveis.put(responsavel, 1);
		}
	}

	public Ocorrencia getOcorrenciaDaLista(Ocorrencia ocorrencia) {
		for (Ocorrencia ocorrenciaValor : this.ocorrencias) {
			if (ocorrenciaValor.equals(ocorrencia)) {
				return ocorrenciaValor;
			}
		}
		throw new CadastroOcorrenciaException("Ocorrencia não cadastrada");
	}

	public void concluirOcorrencia(Ocorrencia ocorrencia) {
		this.getOcorrenciaDaLista(ocorrencia).setEstado(EstadoOcorrencia.COMPLETADA);
	}

	public static void zerarID() {
		ID_ATUAL = 0;
	}
}
