package com.tests.appl;

import com.webcheckers.appl.PlayerSession;
import org.junit.Test;

public class PlayerSessionTest {

    @Test
    private void testPlayerSessionConstructor(){
        new PlayerSession("username","1234");
    }
}