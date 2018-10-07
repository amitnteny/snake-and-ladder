package user;

import domain.Cell;
import domain.PitStop;
import domain.PitStopType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private User user;

    @Before
    public void setup(){
        Cell[] cells = new Cell[9];
        // initializing with an array of 8 cells
        for(int i=0; i<9; i++) {
            Arrays.fill(cells, new Cell(i, new PitStop(PitStopType.LADDER_BOTTOM, i)));
        }
        //A ladder bottom at position 1 with ladder top at 8
        cells[0] = new Cell(0, new PitStop(PitStopType.LADDER_BOTTOM, 8));
        user = new User(cells);
    }

    @Test
    public void shouldRollTheDiceBetweenZeroAndSix(){
         for(int i=0; i<100; i++) {
             int diceRolled = user.rollADice();
             assertTrue(diceRolled > 0 && diceRolled <=6);
         }
    }

    @Test
    public void shouldMoveToNewPosition(){
        int diceRolled = 1;
        user.moveToNewPositionAndPrintScore(0, diceRolled);
        assertEquals(8, user.getCurrentPosition()); //since 1 was ladder bottom and pitstop was 8
    }

}