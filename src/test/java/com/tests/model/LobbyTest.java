package com.tests.model;

import com.webcheckers.model.gamelogic.Lobby;
import org.junit.Test;

/**
 * Unit tests for the Lobby class.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class LobbyTest {
    @Test
    public void testLobbyConstructor(){
        Lobby l = new Lobby();
    }

    @Test
    public void testAddPlayer(){
        Lobby l = new Lobby();
        l.addPlayer("123","testy");
    }

    @Test
    public void testAddGame(){
        Lobby l = new Lobby();
        l.addPlayer("123","testo");
        l.addPlayer("456","testy");
        l.addNewGame("123","456");
    }

    @Test
    public void testGetGame(){
        Lobby l = new Lobby();
        l.addPlayer("123","testo");
        l.addPlayer("456","testy");
        l.addNewGame("123","456");
        l.getGame("1");
    }

    @Test
    public void testGetPlayerBySessionID(){
        Lobby l = new Lobby();
        l.addPlayer("123","testo");
        l.addPlayer("456","testy");
        l.addNewGame("123","456");
        l.getPlayerBySessionID("123");
    }

//    @Test
//    public void testGetPlayer(){
//        Lobby l = new Lobby();
//        l.addPlayer("123","testo");
//        l.addPlayer("456","testy");
//        l.addNewGame("123","456");
//        l.getPlayer("testy");
//    }
}
