package aula_20170404;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import eightPuzzleGame.domain.PuzzleGame;
import eightPuzzleGame.domain.ShufflePuzzleLevelEasy;
import eightPuzzleGame.domain.SquareBoard;
import eightPuzzleGame.domain.Tile;
import eightPuzzleGame.domain.Position;
import eightPuzzleGame.domain.Puzzle.Direction;

public class EightPuzzleGameTest {
	PuzzleGame pg;
	final int tableSize = 3;

	private void printPG() {
		System.out.println(this.pg.toString());
	}

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
	public void moveLeftTest() throws Exception {
		int position = this.pg.getEmptyCell().getColumn() - 1 > 0 ? this.pg.getEmptyCell().getColumn() - 1 : 1;
		this.pg.moveEmptyCell(Direction.LEFT);
		this.printPG();
		assertEquals(position, this.pg.getEmptyCell().getColumn());
	}

	@Test
	public void moveRightTest() throws Exception {
		int positionMaisUm = this.pg.getEmptyCell().getColumn() + 1;
		int position = positionMaisUm < this.tableSize ? positionMaisUm : this.tableSize;
		this.pg.moveEmptyCell(Direction.RIGHT);
		this.printPG();
		assertEquals(position, this.pg.getEmptyCell().getColumn());
	}

	@Test
	public void moveNullTest() throws Exception {
		this.printPG();
		assertEquals(false, this.pg.moveEmptyCell(null));
	}

	@Test
	public void putTilesInTheBoard() throws Exception {
		List<Tile> list = new LinkedList<Tile>();
		list.add(new Tile(10));
		list.add(new Tile(11));
		list.add(new Tile(12));
		list.add(new Tile(13));
		list.add(new Tile(14));
		list.add(new Tile(15));
		list.add(new Tile(16));
		list.add(new Tile(17));
		list.add(new Tile(18));

		this.javaReflection(list);

		this.printPG();
		assertEquals(this.pg.getBoard().getTile(new Position(1, 1)), new Tile(10));
		assertEquals(this.pg.getBoard().getTile(new Position(1, 2)), new Tile(11));
		assertEquals(this.pg.getBoard().getTile(new Position(1, 3)), new Tile(12));
		assertEquals(this.pg.getBoard().getTile(new Position(2, 1)), new Tile(13));
		assertEquals(this.pg.getBoard().getTile(new Position(2, 2)), new Tile(14));
		assertEquals(this.pg.getBoard().getTile(new Position(2, 3)), new Tile(15));
		assertEquals(this.pg.getBoard().getTile(new Position(3, 1)), new Tile(16));
		assertEquals(this.pg.getBoard().getTile(new Position(3, 2)), new Tile(17));
	}

	private void javaReflection(List<Tile> list)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method privateMethod = PuzzleGame.class.getDeclaredMethod("putTilesInTheBoard",
				new Class[] { SquareBoard.class, List.class });

		privateMethod.setAccessible(true);
		privateMethod.invoke(this.pg, this.pg.getBoard(), list);
	}

}
