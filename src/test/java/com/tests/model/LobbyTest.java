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
        Player p = new Player("testy");
        try{
            l.addPlayer(p);
        }
        catch (PlayerNotAddedException e){
            System.out.println("ERROR: Player was not added to lobby");
        }
    }

    @Test
    public void testAddGame(){
        Lobby l = new Lobby();
        Player p1 = new Player("testy");
        Player p2 = new Player("testo");
        CheckersBoard cb = new CheckersBoard(p1,p2);
        try{
            l.addPlayer(p1);
            l.addPlayer(p2);
            l.addGame(cb);
        }
        catch(PlayerNotAddedException e){
            System.out.println("ERROR: Player was not added to lobby");
        }
        catch(GameNotAddedException e){
            System.out.println("ERROR: Game was not added to lobby");
        }
    }

    @Test
    public void testInvalidAddGame(){
        Lobby l = new Lobby();
        Player p1 = new Player("testy");
        Player p2 = new Player("testo");
        Player p3 = new Player("bad man");
        CheckersBoard cb = new CheckersBoard(p1,p2);
        try{
            l.addPlayer(p1);
            l.addPlayer(p3);
            l.addGame(cb);
        }
        catch(PlayerNotAddedException e){
            System.out.println("ERROR: Player was not added to lobby");
        }
        catch(GameNotAddedException e){
            System.out.println("Success: Lobby did not contain proper players to add game");
        }
    }
}
