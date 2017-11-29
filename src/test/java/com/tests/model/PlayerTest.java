package com.tests.model;

import com.webcheckers.model.Player;
import org.junit.Test;

/**
 * Unit tests for the Player class.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class PlayerTest {
    @Test
    public void testPlayerConstructor(){
        new Player("123", "test name");
    }

    @Test
    public void testAddToGame(){
        Player p = new Player("123", "test name");
        p.addToGame("1");
    }

    @Test
    public void testGetIds(){
        Player p = new Player("123", "test name");
        p.getName();
    }
    @Test
    public void testGetName(){
        Player p = new Player("123", "test name");
        p.getIds();
    }
}
