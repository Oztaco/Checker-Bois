package com.tests.model;

import com.webcheckers.model.Game;
import com.webcheckers.model.MoveType;
import com.webcheckers.model.Player;
import org.junit.Test;

/**
 * Unit tests for the Game class.
 * Primarily tests that all four combinations of player and
 * move type are processed correctly by Game.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class GameTest {
    @Test
    public void testGameConstructor(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
    }

    @Test
    public void testPlayTurnMove1(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.playTurn(p1,1,2,0,3, MoveType.MOVE);
    }

    @Test
    public void testPlayTurnMove2(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.playTurn(p2,0,5,1,4, MoveType.MOVE);
    }

    @Test
    public void testPlayTurnAttack1(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.playTurn(p1,3,4,5,6, MoveType.ATTACK);
    }

    @Test
    public void testPlayTurnAttack2(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.playTurn(p1,4,5,2,3, MoveType.ATTACK);
    }
}
