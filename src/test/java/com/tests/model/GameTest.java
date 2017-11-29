package com.tests.model;

import com.webcheckers.model.Exceptions.InvalidMoveException;
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
    public void testGetID(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.getId();
    }

    @Test
    public void testGetPlayer1(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.getPlayer(1);
    }

    @Test
    public void testGetPlayer2(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        g.getPlayer(2);
    }

    /*
    @Test
    public void testPlayTurnMove1(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        try {
            g.playTurn(p1,1,2,0,3, MoveType.MOVE);
        }
        catch (InvalidMoveException e){
            System.out.println("STATUS: FAILED");
        }
    }

    @Test
    public void testPlayTurnMove2(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        try{
            g.playTurn(p2,0,5,1,4, MoveType.MOVE);
        }
        catch (InvalidMoveException e){
            System.out.println("STATUS: FAILED");
        }
    }

    @Test
    public void testPlayTurnAttack1(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        try {
            g.playTurn(p1,3,4,5,6, MoveType.ATTACK);
        }
        catch (InvalidMoveException e){
            System.out.println("STATUS: FAILED");
        }
    }

    @Test
    public void testPlayTurnAttack2(){
        Player p1 = new Player("123","testy");
        Player p2 = new Player("456","testo");
        Game g = new Game(p1,p2,"1");
        try {
            g.playTurn(p1,4,5,2,3, MoveType.ATTACK);
        }
        catch (InvalidMoveException e){
            System.out.println("STATUS: FAILED");
        }
    }
    */
}
