package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;
import com.webcheckers.model.Lobby;
import com.webcheckers.model.Player;
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
}
