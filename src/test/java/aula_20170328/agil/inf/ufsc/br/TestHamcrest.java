package aula_20170328.agil.inf.ufsc.br;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestHamcrest {

	@Test
	public void testAssertEquals() throws Exception {
		assertThat("João", equalTo("João"));
	}

	@Test
	public void testAssertNotEquals() throws Exception {
		assertThat("João", not(equalTo("Maria")));
	}

	@Test
	public void testAssertEqualsWithIs() throws Exception {
		assertThat("João", is(equalTo("João")));
	}

	@Test
	public void testAssertNotEqualsWithDescribedAs() throws Exception {
		assertThat("João", describedAs("João é diferente de Joao", not(equalTo("Joao"))));
	}

	@Test
	public void test() throws Exception {
		assertThat("João", describedAs("João é diferente de Joao", not(equalTo("Joao"))));
	}

	@Test
	public void testAssertHasItem() throws Exception {
		List<String> nomes = Arrays.asList("João", "Maria");
		assertThat(nomes, hasItem("Maria"));
	}

	@Test
	public void testAllOf() throws Exception {
		Integer valor = 10;
		assertThat(valor, allOf(greaterThan(new Integer(0)), lessThanOrEqualTo(new Integer(10))));
	}
}
