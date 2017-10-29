package com.tests.model;

import com.webcheckers.model.Player;
import org.junit.Test;

public class PlayerTest {

    @Test
    private void testPlayerSessionConstructor(){
        new Player("test name");
    }

    @Test
    private void testKingPiece(){
        Player p1 = new Player("p1");
        p1.kingPiece();
    }
}
