// This is a mutant program.
// Author : ysma

package domain;


import java.util.Random;
import domain.Puzzle.Direction;


public class ShufflePuzzleLevelEasy implements domain.StrategyShufflePuzzle
{

    private final int numberOfShuffles = 4;

    public ShufflePuzzleLevelEasy()
    {
    }

    public  void shuffle( domain.Puzzle game )
    {
        java.util.Random generateRandom = new java.util.Random();
        for (int i = 0; i < this.numberOfShuffles; i++) {
            boolean changed = false;
        }
    }

}
