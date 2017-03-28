package aula_20170328;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TesteHamcrest {

	/*
	 * Exercício 1
	 */
	@Test
	public void objectMatcherTest() throws Exception {
		String a = new String();
		String b = new String();
		assertThat(a, instanceOf(b.getClass()));
	}

	@Test
	public void numberMatcherTest() throws Exception {
		assertThat(1, greaterThan(0));
	}

	@Test
	public void textMatcherTest() throws Exception {
		assertThat("Rodrigo", equalTo("Rodrigo"));
	}

	@Test
	public void arrayMatcherTest() throws Exception {
		List<Integer> a = Arrays.asList(1, 2, 3);
		assertThat(a, hasItem(1));
	}

	@Test
	public void allOfMatcherTest() throws Exception {
		String text = "hello world";
		assertThat(text, allOf(startsWith("hello"), endsWith("world")));
	}

	@Test
	public void anyOfMatcherTest() throws Exception {
		String text = "hello world";
		assertThat(text, anyOf(startsWith("hello"), endsWith("world!!")));
	}

	/*
	 * Exercício 2
	 */
	// go to TesteAgenciaHamcrest.java

	/*
	 * Exercício 3
	 */
	// TODO
}
