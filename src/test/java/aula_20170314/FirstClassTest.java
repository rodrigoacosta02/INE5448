package aula_20170314;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

public class FirstClassTest {

	@Test
	public void firstTest() throws Exception {
		LocalDate diaDeHoje = new LocalDate(2017,3,14);
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
		LocalDate anoBissexto = new LocalDate(2017,01,01);
		int year = anoBissexto.getYear();
		assertNotEquals("Esse ano é bissexto",year%4, 0);
	}

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

	@Test
	public void virandoMeioDia() throws Exception {
		LocalTime meioDia = new LocalTime(11, 59, 59);
		meioDia = meioDia.plusSeconds(1);
		int hour = meioDia.getHourOfDay();
		int min = meioDia.getMinuteOfHour();
		assertEquals(hour, 12);
		assertEquals(min, 0);
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
	//todo -- interval dateTime

}
