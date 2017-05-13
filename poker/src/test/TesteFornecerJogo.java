package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AnalisadorDeJogos;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Naipe;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.RankingDeMao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Valor;

public class TesteFornecerJogo {

	private List<Carta> royalFlush() {
		List<Carta> royalFlush = new ArrayList<>();
		royalFlush.add(new Carta(Valor.ÁS, Naipe.COPAS));
		royalFlush.add(new Carta(Valor.REI, Naipe.COPAS));
		royalFlush.add(new Carta(Valor.DAMA, Naipe.COPAS));
		royalFlush.add(new Carta(Valor.VALETE, Naipe.COPAS));
		royalFlush.add(new Carta(Valor.DEZ, Naipe.COPAS));
		return royalFlush;
	}

	private List<Carta> straighFlush() {
		List<Carta> straighFlush = new ArrayList<>();
		straighFlush.add(new Carta(Valor.CINCO, Naipe.ESPADAS));
		straighFlush.add(new Carta(Valor.SEIS, Naipe.ESPADAS));
		straighFlush.add(new Carta(Valor.SETE, Naipe.ESPADAS));
		straighFlush.add(new Carta(Valor.OITO, Naipe.ESPADAS));
		straighFlush.add(new Carta(Valor.NOVE, Naipe.ESPADAS));
		return straighFlush;
	}

	private List<Carta> fourOfAKind() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.DEZ, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.DEZ, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.DEZ, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.DEZ, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.DAMA, Naipe.ESPADAS));
		return fourOfAKind;
	}

	private List<Carta> fullHouse() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.DAMA, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.DAMA, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.DAMA, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.CINCO, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.CINCO, Naipe.PAUS));
		return fourOfAKind;
	}

	private List<Carta> flush() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.REI, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.DEZ, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.SEIS, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.TRÊS, Naipe.COPAS));
		return fourOfAKind;
	}

	private List<Carta> straight() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.QUATRO, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.CINCO, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.SEIS, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.OITO, Naipe.PAUS));
		return fourOfAKind;
	}

	private List<Carta> threeOfAKind() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.ÁS, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.NOVE, Naipe.COPAS));
		return fourOfAKind;
	}

	private List<Carta> twoPair() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.ÁS, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.ÁS, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.SEIS, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.SEIS, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.DOIS, Naipe.ESPADAS));
		return fourOfAKind;
	}

	private List<Carta> onePair() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.REI, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.REI, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.ÁS, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.QUATRO, Naipe.ESPADAS));
		return fourOfAKind;
	}

	private List<Carta> highCard() {
		List<Carta> fourOfAKind = new ArrayList<>();
		fourOfAKind.add(new Carta(Valor.DAMA, Naipe.PAUS));
		fourOfAKind.add(new Carta(Valor.CINCO, Naipe.COPAS));
		fourOfAKind.add(new Carta(Valor.DOIS, Naipe.ESPADAS));
		fourOfAKind.add(new Carta(Valor.SETE, Naipe.OURO));
		fourOfAKind.add(new Carta(Valor.OITO, Naipe.PAUS));
		return fourOfAKind;
	}

	@Test
	public void testRoyalFlush() throws Exception {
		assertEquals(RankingDeMao.ROYAL_FLUSH, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.royalFlush()));
	}

	@Test
	public void testStraighFlush() throws Exception {
		assertEquals(RankingDeMao.STRAIGHT_FLUSH,
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.straighFlush()));
	}

	@Test
	public void testFourOfAKind() throws Exception {
		assertEquals(RankingDeMao.QUADRA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.fourOfAKind()));
	}

	@Test
	public void testFullHouse() throws Exception {
		assertEquals(RankingDeMao.FULL_HOUSE, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.fullHouse()));
	}

	@Test
	public void testFlush() throws Exception {
		assertEquals(RankingDeMao.FLUSH, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.flush()));
	}

	@Test
	public void testStraight() throws Exception {
		assertEquals(RankingDeMao.STRAIGHT, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.straight()));
	}

	@Test
	public void testThreeOfAKind() throws Exception {
		assertEquals(RankingDeMao.TRINCA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.threeOfAKind()));
	}

	@Test
	public void testTwoPair() throws Exception {
		assertEquals(RankingDeMao.DOIS_PARES, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.twoPair()));
	}

	@Test
	public void testOnePair() throws Exception {
		assertEquals(RankingDeMao.PAR, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.onePair()));
	}

	@Test
	public void testHighCard() throws Exception {
		assertEquals(RankingDeMao.CARTA_ALTA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(this.highCard()));
	}

	@Test(expected = Exception.class)
	public void testNoCards() throws Exception {
		List<Carta> semCartas = new ArrayList<>();
		AnalisadorDeJogos.fornecerInstância().fornecerJogo(semCartas);
	}

	@Test(expected = Exception.class)
	public void testFourCards() throws Exception {
		List<Carta> noCards = new ArrayList<>();
		noCards.add(new Carta(Valor.DAMA, Naipe.PAUS));
		noCards.add(new Carta(Valor.CINCO, Naipe.COPAS));
		noCards.add(new Carta(Valor.DOIS, Naipe.ESPADAS));
		noCards.add(new Carta(Valor.SETE, Naipe.OURO));
		AnalisadorDeJogos.fornecerInstância().fornecerJogo(noCards);
	}

}
