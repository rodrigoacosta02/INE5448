package aula_20170404;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

import org.junit.After;
import eightPuzzleGame.domain.PuzzleGame;
import eightPuzzleGame.domain.ShufflePuzzleLevelEasy;
import eightPuzzleGame.domain.Puzzle.Direction;

public class EightPuzzleGameTest {
	PuzzleGame pg;
	final int tableSize = 3;

	@Rule
	public TestName name = new TestName();

	@Before
	public void before() {
		System.out.println("\nInicio do teste -> " + this.name.getMethodName());
		this.pg = new PuzzleGame(3, new ShufflePuzzleLevelEasy());
		this.printPG();
	}

	@After
	public void after() {
		System.out.println("Fim do teste \t-> " + this.name.getMethodName());

	}

	@Test
	public void moveDownTest() throws Exception {
		int positionMaisUm = this.pg.getEmptyCell().getLine() + 1;
		int position = positionMaisUm < this.tableSize ? positionMaisUm : this.tableSize;
		this.pg.moveEmptyCell(Direction.DOWN);
		this.printPG();
		assertEquals(position, this.pg.getEmptyCell().getLine());
	}

	@Test
	public void moveUpTest() throws Exception {
		int position = this.pg.getEmptyCell().getLine() - 1 > 0 ? this.pg.getEmptyCell().getLine() - 1 : 1;
		this.pg.moveEmptyCell(Direction.UP);
		this.printPG();
		assertEquals(position, this.pg.getEmptyCell().getLine());
	}

	@Test
	public void moveNullTest() throws Exception {
		this.printPG();
		assertEquals(false, this.pg.moveEmptyCell(null));
	}

	@Test
	public void moveTileTest() throws Exception {
		int position = this.pg.getEmptyCell().getLine() > 0 ? this.pg.getEmptyCell().getLine() : 1;
		this.pg.moveTile(1);
		this.printPG();
		assertEquals(position, this.pg.getEmptyCell().getLine());
	}

	private void printPG() {
		System.out.println(this.pg.toString());
	}

}
