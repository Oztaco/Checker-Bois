package com.tests.model;

import com.webcheckers.model.gamelogic.MoveType;
import com.webcheckers.model.gamelogic.PastMove;
import com.webcheckers.model.gamelogic.Player;
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

    @Test
    public void testGetX0(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getX0();
    }

    @Test
    public void testGetY0(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getY0();
    }

    @Test
    public void testGetX1(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getX1();
    }

    @Test
    public void testGetY1(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getY1();
    }

    @Test
    public void testGetType(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getType();
    }

    @Test
    public void testGetPlayerPastMove(){
        Player p = new Player("test","johnny");
        PastMove pm = new PastMove(0,1,2,3, MoveType.MOVE,p);
        pm.getPlayerPastMove();
    }
}
