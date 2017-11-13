package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;
import com.webcheckers.model.Lobby;
import com.webcheckers.model.Player;
import org.junit.Test;

public class LobbyTest {

    @Test
    public void testLobbyConstructor(){
        Lobby l = new Lobby();
    }

    @Test
    public void testAddPlayer(){
        Lobby l = new Lobby();
        Player p = new Player("123","testy");
        l.addPlayer("123","testy");
    }

    @Test
    public void testAddGame(){
        Lobby l = new Lobby();
        l.addPlayer("123","testo");
        l.addPlayer("123","testo");
        l.addNewGame("testy","testo");
    }

    @Test
    public void testInvalidAddGame(){
        Lobby l = new Lobby();
        l.addPlayer("123", "testy");
        l.addPlayer("123", "testy");
        l.addNewGame("testy", "testo");
    }
}
