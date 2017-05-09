// This is a mutant program.
// Author : ysma

package domain;


import exceptions.ExceptionInvalidPosition;


public class Board
{

    private int line;

    private int column;

    private domain.Grid<Cell> grid;

    public Board( int line, int column )
    {
        this.line = line;
        this.column = column;
        this.grid = new domain.Grid<Cell>( line, column );
        this.initializeAllCells();
    }

    private  void initializeAllCells()
    {
        for (int i = 1; i <= this.line; i++) {
            for (int j = 1; j <= this.column; j++) {
                this.grid.put( i, j, new domain.Cell() );
            }
        }
    }

    public  void putTile( domain.Tile tile, domain.Position position )
    {
        this.grid.get( position.getLine(), position.getColumn() ).putTile( tile );
    }

    public  domain.Tile getTile( domain.Position position )
    {
        if (this.isInsideTheBoard( position )) {
            return grid.get( position.getLine(), position.getColumn() ).tile;
        }
        return null;
    }

    public  boolean isAdjacent( domain.Position position1, domain.Position position2 )
    {
        return this.isInsideTheBoard( position1 ) && this.isInsideTheBoard( position2 ) && (position1.getLine() == position2.getLine() && Math.abs( position1.getColumn() - position2.getColumn() ) == 1 || position1.getColumn() == position2.getColumn() && Math.abs( position1.getLine() - position2.getLine() ) == 1);
    }

    public  boolean changeTilesInPositions( domain.Position position1, domain.Position position2 )
        throws exceptions.ExceptionInvalidPosition
    {
        if (this.isInsideTheBoard( position1 ) && this.isInsideTheBoard( position2 )) {
            domain.Cell cell1 = this.grid.get( position1.getLine(), position1.getColumn() );
            domain.Cell cell2 = this.grid.get( position2.getLine(), position2.getColumn() );
            domain.Tile aux = cell1.getTile();
            cell1.putTile( cell2.getTile() );
            cell2.putTile( aux );
            return true;
        } else {
            throw new exceptions.ExceptionInvalidPosition();
        }
    }

    public  boolean isInTheSuperiorBorder( domain.Position position )
    {
        return this.isInsideTheBoard( position ) && position.getLine() == 1;
    }

    public  boolean isInTheBottomBorder( domain.Position position )
    {
        return this.isInsideTheBoard( position ) && position.getLine() == this.line;
    }

    public  boolean isInTheLeftBorder( domain.Position position )
    {
        return this.isInsideTheBoard( position ) && position.getColumn() == 1;
    }

    public  boolean isInTheRightBorder( domain.Position position )
    {
        return this.isInsideTheBoard( position ) && position.getColumn() == this.column;
    }

    private  boolean isInsideTheBoard( domain.Position position )
    {
        return position.getLine() > 0 && position.getLine() <= this.line && position.getColumn() > 0 && position.getColumn() <= this.column;
    }

    public  boolean equals( java.lang.Object board )
    {
        if (this.line != ((domain.Board) board).line || this.column != ((domain.Board) board).column) {
            return false;
        }
        for (int i = 1; i <= this.line; i++) {
            for (int j = 1; j <= this.column; j++) {
                if (!this.grid.get( i, j ).equals( ((domain.Board) board).grid.get( i, ~j ) )) {
                    return false;
                }
            }
        }
        return true;
    }

    public  java.lang.String toString()
    {
        java.lang.String result = "";
        for (int i = 1; i <= this.line; i++) {
            for (int j = 1; j <= this.column; j++) {
                domain.Cell cell = grid.get( i, j );
                result = result + "(" + i + "," + j + ")" + ":" + cell.toString() + "   ";
            }
            result = result + "\n";
        }
        return result;
    }

}
