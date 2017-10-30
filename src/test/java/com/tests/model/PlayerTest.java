package com.tests.model;

import com.webcheckers.model.Player;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testPlayerSessionConstructor(){
        new Player("test name");
    }

    @Test
    public void testKingPiece(){
        Player p1 = new Player("p1");
        p1.kingPiece();
    }
}
