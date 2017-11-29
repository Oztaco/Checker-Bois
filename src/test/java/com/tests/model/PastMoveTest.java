package com.tests.model;

import com.webcheckers.model.MoveType;
import com.webcheckers.model.PastMove;
import com.webcheckers.model.Player;
import org.junit.Test;

/**
 * Unit tests for the PastMove class.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class PastMoveTest {
    @Test
    public void testPastMoveConstructor(){
        Player p = new Player("test","johnny");
        new PastMove(0,0,1,1, MoveType.ATTACK,p);
    }
}
