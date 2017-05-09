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
        for (int i = 0; i < this.numberOfShuffles;  ) {
            boolean changed = false;
            do {
                int direction = generateRandom.nextInt( 4 );
                if (direction == 0) {
                    changed = game.moveEmptyCell( Direction.UP );
                }
                if (direction == 1) {
                    changed = game.moveEmptyCell( Direction.DOWN );
                }
                if (direction == 2) {
                    changed = game.moveEmptyCell( Direction.LEFT );
                }
                if (direction == 3) {
                    changed = game.moveEmptyCell( Direction.RIGHT );
                }
            } while (!changed);
        }
    }

}
