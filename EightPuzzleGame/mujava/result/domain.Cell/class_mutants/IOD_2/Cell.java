// This is a mutant program.
// Author : ysma

package domain;


public class Cell
{

    domain.Tile tile;

    public Cell()
    {
    }

    public  void putTile( domain.Tile tile )
    {
        this.tile = tile;
    }

    public  domain.Tile removeTile()
    {
        domain.Tile aux = this.tile;
        this.tile = null;
        return aux;
    }

    public  domain.Tile getTile()
    {
        return this.tile;
    }

    public  boolean equals( java.lang.Object cell )
    {
        if (this.tile == null && ((domain.Cell) cell).tile == null) {
            return true;
        } else {
            if (this.tile == null || ((domain.Cell) cell).tile == null) {
                return false;
            } else {
                return this.tile.equals( ((domain.Cell) cell).tile );
            }
        }
    }

    // public java.lang.String toString(){ ... }

}
