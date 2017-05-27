package br.inf.ufsc.ocorrencias.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.inf.ufsc.ocorrencias.enums.EstadoOcorrencia;
import br.inf.ufsc.ocorrencias.enums.PrioridadeOcorrencia;
import br.inf.ufsc.ocorrencias.enums.TipoOcorrencia;

public class EnumTest {

	@Test
	public void estadoOcorrencia() throws Exception {
		String[] valuesEnum = { "ABERTA", "COMPLETADA" };
		int i = 0;
		for (EstadoOcorrencia estado : EstadoOcorrencia.values()) {
			assertEquals(estado, EstadoOcorrencia.valueOf(valuesEnum[i++]));
		}
	}

	@Test
	public void tipoOcorrencia() throws Exception {
		String[] valuesEnum = { "TAREFA", "BUG", "MELHORIA" };
		int i = 0;
		for (TipoOcorrencia tipo : TipoOcorrencia.values()) {
			assertEquals(tipo, TipoOcorrencia.valueOf(valuesEnum[i++]));
		}
	}

	@Test
	public void prioridadeOcorrencia() throws Exception {
		String[] valuesEnum = { "ALTA", "MEDIA", "BAIXA" };
		int i = 0;
		for (PrioridadeOcorrencia prioridade : PrioridadeOcorrencia.values()) {
			assertEquals(prioridade, PrioridadeOcorrencia.valueOf(valuesEnum[i++]));
		}
	}
}
