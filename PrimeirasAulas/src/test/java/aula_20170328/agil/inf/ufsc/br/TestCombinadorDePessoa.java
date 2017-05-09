package aula_20170328.agil.inf.ufsc.br;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCombinadorDePessoa {

@Test
public void joao() {
	Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
	assertEquals("João", joao.obterNome());
	assertEquals(Integer.valueOf(17), joao.obterIdade());
	assertEquals(Ocupacao.ESTUDANTE, joao.obterOcupacao());
}

@Test
public void maria() {
	Pessoa maria = new Pessoa(1, "Maria", 25, Ocupacao.PROFESSORA);
	assertEquals("Maria", maria.obterNome());
	assertEquals(Integer.valueOf(25), maria.obterIdade());
	assertEquals(Ocupacao.PROFESSORA, maria.obterOcupacao());
}

@Test
public void joaoWithCustomAssert() {
	Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
	assertPessoa(joao, "João", 17, Ocupacao.ESTUDANTE);
}

@Test
public void joaoWithCustomAssertFailure() {
	Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
	assertPessoa(joao, "Maria", 25, Ocupacao.PROFESSORA);
}

@Test
public void mariaWithCustomAssert() {
	Pessoa maria = new Pessoa(1, "Maria", 25, Ocupacao.PROFESSORA);
	assertPessoa(maria, "Maria", 25, Ocupacao.PROFESSORA);
}

public static void assertPessoa(Pessoa pessoa, String nome, Integer idade, Ocupacao ocupacao) {
	assertEquals(nome, pessoa.obterNome());
	assertEquals(idade, pessoa.obterIdade());
	assertEquals(ocupacao, pessoa.obterOcupacao());
}

@Test
public void joaoWitHamcrestMatcher() {
	Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
	assertThat(joao, new CombinadorDePessoa("João", 17, Ocupacao.ESTUDANTE));
}

@Test
public void mariaWitHamcrestMatcher() {
	Pessoa maria = new Pessoa(1, "Maria", 25, Ocupacao.PROFESSORA);
	assertThat(maria, new CombinadorDePessoa("Maria", 25, Ocupacao.PROFESSORA));
}

@Test
public void joaoWitHamcrestMatcherFailure() {
	Pessoa joao = new Pessoa(1, "João", 17, Ocupacao.ESTUDANTE);
	assertThat(joao, new CombinadorDePessoa("Marua", 25, Ocupacao.PROFESSORA));
}

}
