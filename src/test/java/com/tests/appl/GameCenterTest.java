package com.tests.appl;
import com.webcheckers.appl.GameCenter;
import org.junit.Test;

import com.webcheckers.appl.GameCenter;
import org.junit.Test;

/**
 * Unit tests for the GameCenter class.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class GameCenterTest {
    @Test
    public void testGameCenterConstructor(){
        GameCenter gc = new GameCenter();
    }

    @Test
    public void testAddPlayer(){
        GameCenter gc = new GameCenter();
        gc.getLobby().addPlayer("123","testy");
    }

    @Test
    public void testAddNewGame(){
        GameCenter gc = new GameCenter();
        gc.getLobby().addPlayer("123","testy");
        gc.getLobby().addPlayer("456","testo");
        gc.getLobby().addNewGame("123","456");
    }
}