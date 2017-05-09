package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AnalisadorDeJogos;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Naipe;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.RankingDeMao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Valor;

public class TesteFornecerJogoInLineSetup {

	private List<Carta> listaDeCartas() {
		List<Carta> parDeAs = new ArrayList<>();
		parDeAs.add(new Carta(Valor.ÁS, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.ÁS, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.CINCO, Naipe.PAUS));
		parDeAs.add(new Carta(Valor.SETE, Naipe.OURO));
		parDeAs.add(new Carta(Valor.NOVE, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.VALETE, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.REI, Naipe.PAUS));
		return parDeAs;
	}

	/*
	 * in-line setup
	 */
	public void testFornecerJogo() throws Exception {
		List<Carta> parDeAs = this.listaDeCartas();
		assertEquals(RankingDeMao.PAR, AnalisadorDeJogos.fornecerInstância().fornecerJogo(parDeAs));
	}

}
