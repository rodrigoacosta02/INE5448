package aula_20170314;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class FirstClassTest {

	/*
	 * Criação de data
	 */

	@Test
	public void firstTest() throws Exception {
		LocalDate diaDeHoje = new LocalDate(2017, 3, 14);
		assertEquals(14, diaDeHoje.getDayOfMonth());
		assertEquals(3, diaDeHoje.getMonthOfYear());
		assertEquals(2017, diaDeHoje.getYear());
	}

	@Test(expected = IllegalFieldValueException.class)
	public void exceptionField() throws Exception {
		new LocalDate(2000, 12, -1);
	}

	@Test
	public void notBissexto() throws Exception {
		LocalDate anoBissexto = new LocalDate(2017, 01, 01);
		int year = anoBissexto.getYear();
		assertNotEquals("Esse ano é bissexto", year % 4, 0);
	}

	/*
	 * Soma de datas
	 */

	@Test
	public void somaDeData() throws Exception {
		LocalDate date = new LocalDate(2017, 01, 01);
		LocalDate datePlusOne = new LocalDate(2017, 01, 02);
		assertEquals(date.plusDays(1), datePlusOne);
	}

	/*
	 * Subtração de datas
	 */

	@Test
	public void subtracaoDeData() throws Exception {
		LocalDate date = new LocalDate(2017, 01, 01);
		LocalDate datePlusOne = new LocalDate(2017, 01, 02);
		assertEquals(date, datePlusOne.minusDays(1));
	}

	/*
	 * Criação de hora
	 */

	@Test
	public void meioDia() throws Exception {
		LocalTime meioDia = new LocalTime(12, 00, 00);
		int hour = meioDia.getHourOfDay();
		int min = meioDia.getMinuteOfHour();
		assertEquals(hour, 12);
		assertEquals(min, 0);
	}

	@Test
	public void meiaNoite() throws Exception {
		LocalTime meiaNoite = new LocalTime(0, 00, 00);
		int hour = meiaNoite.getHourOfDay();
		int min = meiaNoite.getMinuteOfHour();
		assertEquals(hour, 0);
		assertEquals(min, 0);
	}

	/*
	 * Soma de hora
	 */
	@Test
	public void virandoMeioDia() throws Exception {

	}

	@Test
	public void virandoMeiaNoite() throws Exception {
		LocalTime meiaNoite = new LocalTime(23, 59, 59);
		meiaNoite = meiaNoite.plusSeconds(1);
		int hour = meiaNoite.getHourOfDay();
		int min = meiaNoite.getMinuteOfHour();
		assertEquals(hour, 0);
		assertEquals(min, 0);
	}

	@Test
	public void passandoMeioDia() throws Exception {
		LocalTime meioDia = new LocalTime(12, 0, 0);
		meioDia = meioDia.plusMinutes(1);
		int hour = meioDia.getHourOfDay();
		int min = meioDia.getMinuteOfHour();
		assertEquals(hour, 12);
		assertEquals(min, 1);
	}

	@Test
	public void passandoMeiaNoite() throws Exception {
		LocalTime meiaNoite = new LocalTime(0, 0, 0);
		meiaNoite = meiaNoite.plusMinutes(1);
		int hour = meiaNoite.getHourOfDay();
		int min = meiaNoite.getMinuteOfHour();
		assertEquals(hour, 0);
		assertEquals(min, 1);
	}

	@Test
	public void passandoMeioDiaV2() throws Exception {
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime meioDiaEUm = new LocalTime(12, 1, 0);
		assertEquals(meioDiaEUm, meioDia.plusMinutes(1));
	}

	@Test
	public void passandoMeiaNoiteV2() throws Exception {
		LocalTime meiaNoite = new LocalTime(0, 0, 0);
		LocalTime meioDiaEUm = new LocalTime(0, 1, 0);
		assertEquals(meioDiaEUm, meiaNoite.plusMinutes(1));
	}

	@Test
	public void virandoDia() throws Exception {
		LocalDateTime meiaNoite = new LocalDateTime(2017, 03, 15, 23, 59, 59);
		int today = meiaNoite.getDayOfMonth();
		int tomorrow = meiaNoite.plusSeconds(1).getDayOfMonth();
		assertEquals(today + 1, tomorrow);
	}

	/*
	 * Subtração de horas
	 */

	@Test
	public void virandoDiaPraTras() throws Exception {
		LocalDateTime meiaNoite = new LocalDateTime(2017, 03, 15, 00, 00, 00);
		int today = meiaNoite.getDayOfMonth();
		int tomorrow = meiaNoite.minusSeconds(1).getDayOfMonth();
		assertEquals(today - 1, tomorrow);
	}

	/*
	 * Before e After
	 */

	@Rule
	public TestName name = new TestName();

	@BeforeClass
	public static void beforeClass() {
		System.out.println("\nInicio da Classe de testes");
	}

	@Before
	public void before() {
		System.out.println("\nInicio do teste -> " + this.name.getMethodName());
	}

	@After
	public void after() {
		System.out.println("Fim do teste \t-> " + this.name.getMethodName());

	}

	@AfterClass
	public static void afterClass() {
		System.out.println("\nFim da Classe de testes");
	}
	/*
	 * Criação de data/hora
	 */

	@Test
	public void dataHoraCriacao() throws Exception {
		LocalDateTime meiaNoite = new LocalDateTime(2017, 03, 15, 10, 15, 35);
		assertEquals(15, meiaNoite.getDayOfMonth());
		assertEquals(3, meiaNoite.getMonthOfYear());
		assertEquals(2017, meiaNoite.getYear());
		assertEquals(10, meiaNoite.getHourOfDay());
		assertEquals(15, meiaNoite.getMinuteOfHour());
		assertEquals(35, meiaNoite.getSecondOfMinute());
	}

	@Test(expected = IllegalFieldValueException.class)
	public void horaDataErrada() throws Exception {
		new LocalDateTime(2017, 03, 15, 00, 00, -04);
	}

	/*
	 * intervalo de datas (Interval)
	 */

	@Test
	public void itervaloDeDatas() {
		DateTime segunda = new DateTime(2017, 03, 13, 12, 12, 12);
		DateTime terca = new DateTime(2017, 03, 14, 12, 12, 12);
		DateTime quarta = new DateTime(2017, 03, 15, 12, 12, 12);
		DateTime quinta = new DateTime(2017, 03, 16, 12, 12, 12);
		DateTime sexta = new DateTime(2017, 03, 17, 12, 12, 12);
		Interval interval = new Interval(terca, quinta);

		assertTrue(interval.isAfter(segunda));
		assertTrue(interval.contains(quarta));
		assertTrue(interval.isBefore(sexta));

	}

	/*
	 * Criação de períodos
	 */

	@Test
	public void periodoHora() throws Exception {
		LocalTime inicio = new LocalTime(12, 00, 00);
		LocalTime fim = new LocalTime(13, 30, 00);
		Period periodo = new Period(inicio, fim);

		assertEquals(1, periodo.getHours());
		assertEquals(30, periodo.getMinutes());
	}

	@Test
	public void periodoMes() throws Exception {
		LocalDate inicio = new LocalDate(2017, 01, 01);
		LocalDate fim = new LocalDate(2017, 02, 01);
		Period periodo = new Period(inicio, fim);

		assertEquals(1, periodo.getMonths());
		assertEquals(0, periodo.getDays());
	}

	/*
	 * Others...
	 */

	// TODO

}
