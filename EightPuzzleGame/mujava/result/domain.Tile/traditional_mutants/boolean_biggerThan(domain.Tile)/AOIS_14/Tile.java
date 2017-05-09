// This is a mutant program.
// Author : ysma

package domain;


public class Tile
{

    int number;

    int hash;

    public Tile( int number )
    {
        this.number = number;
        this.hash = (new java.lang.Integer( this.number )).hashCode();
    }

    public  int getNumber()
    {
        return this.number;
    }

    public  boolean biggerThan( domain.Tile otherTile )
    {
        return this.number-- > otherTile.number;
    }

    public  boolean equals( java.lang.Object tile )
    {
        return this.number == ((domain.Tile) tile).number;
    }

    public  int hashCode()
    {
        return hash;
    }

    public  java.lang.String toString()
    {
        return String.valueOf( this.number );
    }

}
