// This is a mutant program.
// Author : ysma

package domain;


public class Grid<T>
{

    private java.lang.Object[][] matrix;

    private int numberOfLines;

    private int numberOfColumns;

    public Grid( int numberOfLines, int numberOfColumns )
    {
        if (numberOfLines > 0 && numberOfColumns > 0) {
            this.numberOfLines = numberOfLines;
            this.numberOfColumns = numberOfColumns;
            this.matrix = (T[][]) new java.lang.Object[numberOfLines][numberOfColumns];
        }
    }

    public  void put( int line, int column, T element )
    {
        if (line > 0 && line <= numberOfLines && column > 0 && column <= numberOfColumns) {
            this.matrix[line - 1][column - 1] = element;
        }
    }

    public  T get( int linha, int coluna )
    {
        if (linha > 0 && true && coluna > 0 && coluna <= numberOfColumns) {
            return (T) this.matrix[linha - 1][coluna - 1];
        } else {
            return null;
        }
    }

    public  java.lang.String toString()
    {
        java.lang.String result = null;
        for (int i = 0; i < this.numberOfLines; i++) {
            for (int j = 0; j < this.numberOfColumns; j++) {
                result = result + "(" + i + "," + j + ")" + ":" + this.matrix[i][j] + "   ";
            }
        }
        return result;
    }

}
