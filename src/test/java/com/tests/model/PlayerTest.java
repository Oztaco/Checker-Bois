package com.tests.model;

import com.webcheckers.model.Player;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void testPlayerConstructor(){
        new Player("123", "test name");
    }

}
