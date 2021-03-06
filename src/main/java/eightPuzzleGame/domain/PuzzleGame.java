package eightPuzzleGame.domain;

import java.util.*;

import eightPuzzleGame.exceptions.ExceptionInvalidPosition;

public class PuzzleGame implements Puzzle {

	private final SquareBoard board;
	private final Map<Tile, Position> positionsOfTiles;

	private Position emptyCell;
	private final SquareBoard boardWithFinalState;

	public SquareBoard getBoard() {
		return this.board;
	}

	public Map<Tile, Position> getPositionsOfTiles() {
		return this.positionsOfTiles;
	}

	public Position getEmptyCell() {
		return this.emptyCell;
	}

	public SquareBoard getBoardWithFinalState() {
		return this.boardWithFinalState;
	}

	public PuzzleGame(int dimension, StrategyShufflePuzzle shuffler) {
		this.boardWithFinalState = new SquareBoard(dimension);
		this.board = new SquareBoard(dimension);
		this.positionsOfTiles = new HashMap<Tile, Position>();

		List<Tile> listOfTiles = this.generateListOfTiles(dimension * dimension - 1);

		this.putTilesInTheBoard(this.boardWithFinalState, listOfTiles);

		this.putTilesInTheBoard(this.board, listOfTiles);
		this.putTilesInTheMapOfPositions(this.positionsOfTiles, listOfTiles, dimension);
		this.emptyCell = new Position(dimension, dimension);

		shuffler.shuffle(this);

	}

	private void putTilesInTheBoard(SquareBoard t, List<Tile> list) {
		Iterator<Tile> tilesListIterator = list.iterator();
		// from first line to the line before the last
		for (int line = 1; line < t.getDimension(); line++) {
			for (int column = 1; column <= t.getDimension(); column++) {
				Tile tile = tilesListIterator.next();
				Position position = new Position(line, column);
				t.putTile(tile, position);
				this.positionsOfTiles.put(tile, position);
			}
		}
		// last line
		for (int columm = 1; columm < t.getDimension(); columm++) {
			Tile tile = tilesListIterator.next();
			Position position = new Position(t.getDimension(), columm);
			t.putTile(tile, position);
			this.positionsOfTiles.put(tile, position);
		}
	}

	private void putTilesInTheMapOfPositions(Map<Tile, Position> positions, List<Tile> list, int dimension) {
		Iterator<Tile> tilesListIterator = list.iterator();
		// from first line to the line before the last
		for (int line = 1; line < dimension; line++) {
			for (int column = 1; column <= dimension; column++) {
				Tile tile = tilesListIterator.next();
				Position position = new Position(line, column);
				positions.put(tile, position);
			}
		}
		// last line
		for (int column = 1; column < dimension; column++) {
			Tile tile = tilesListIterator.next();
			Position position = new Position(dimension, column);
			positions.put(tile, position);
		}
	}

	private List<Tile> generateListOfTiles(int quantityOfTiles) {
		List<Tile> listOfTiles = new ArrayList<Tile>();
		for (int i = 1; i <= quantityOfTiles; i++) {
			listOfTiles.add(new Tile(i));
		}
		return listOfTiles;
	}

	public void moveTile(int tileNumber) throws ExceptionInvalidPosition {
		Position tilePosition = this.positionsOfTiles.get(new Tile(tileNumber));
		if (tilePosition != null && this.board.isAdjacent(tilePosition, this.emptyCell)) {
			if (this.board.changeTilesInPositions(tilePosition, this.emptyCell)) {
				this.positionsOfTiles.put(new Tile(tileNumber), this.emptyCell);
				this.emptyCell = tilePosition;
			}
		}
	}

	public void moveTile(int line, int column) throws ExceptionInvalidPosition {
		if (line > 0 && line <= this.board.getDimension() && column > 0 && column <= this.board.getDimension()) {
			Position posicaoPeca = new Position(line, column);
			if (this.board.isAdjacent(posicaoPeca, this.emptyCell)) {
				Tile peca = this.board.getTile(posicaoPeca);
				if (this.board.changeTilesInPositions(posicaoPeca, this.emptyCell)) {
					this.positionsOfTiles.put(peca, this.emptyCell);
					this.emptyCell = posicaoPeca;
				}
			}
		}
	}

	public boolean moveEmptyCell(Direction direction) {
		if (direction == Direction.UP) {
			return this.moveUp();
		} else if (direction == Direction.DOWN) {
			return this.moveDown();
		} else if (direction == Direction.LEFT) {
			return this.moveLeft();
		} else if (direction == Direction.RIGHT) {
			return this.moveRight();
		}
		return false;
	}

	private boolean moveUp() {
		if (this.board.isInTheSuperiorBorder(this.emptyCell)) {
			return false;
		} else {
			Position tilePosition = new Position(this.emptyCell.line - 1, this.emptyCell.column);
			Tile tile = this.board.getTile(tilePosition);
			try {
				this.board.changeTilesInPositions(tilePosition, this.emptyCell);
			} catch (ExceptionInvalidPosition exception) {
			}
			this.positionsOfTiles.put(tile, this.emptyCell);
			this.emptyCell = tilePosition;
			return true;
		}
	}

	private boolean moveDown() {
		if (this.board.isInTheBottomBorder(this.emptyCell)) {
			return false;
		} else {
			Position tilePosition = new Position(this.emptyCell.line + 1, this.emptyCell.column);
			Tile tile = this.board.getTile(tilePosition);
			try {
				this.board.changeTilesInPositions(tilePosition, this.emptyCell);
			} catch (ExceptionInvalidPosition exception) {
			}
			this.positionsOfTiles.put(tile, this.emptyCell);
			this.emptyCell = tilePosition;
			return true;
		}
	}

	private boolean moveLeft() {
		if (this.board.isInTheLeftBorder(this.emptyCell)) {
			return false;
		} else {
			Position tilePosition = new Position(this.emptyCell.line, this.emptyCell.column - 1);
			Tile tile = this.board.getTile(tilePosition);
			try {
				this.board.changeTilesInPositions(tilePosition, this.emptyCell);
			} catch (ExceptionInvalidPosition exception) {
			}
			this.positionsOfTiles.put(tile, this.emptyCell);
			this.emptyCell = tilePosition;
			return true;
		}
	}

	private boolean moveRight() {
		if (this.board.isInTheRightBorder(this.emptyCell)) {
			return false;
		} else {
			Position tilePosition = new Position(this.emptyCell.line, this.emptyCell.column + 1);
			Tile tile = this.board.getTile(tilePosition);
			try {
				this.board.changeTilesInPositions(tilePosition, this.emptyCell);
			} catch (ExceptionInvalidPosition exception) {
			}
			this.positionsOfTiles.put(tile, this.emptyCell);
			this.emptyCell = tilePosition;
			return true;
		}
	}

	public boolean endOfTheGame() {
		return this.board.equals(this.boardWithFinalState);
	}

	@Override
	public String toString() {
		return this.board.toString();
	}

}
