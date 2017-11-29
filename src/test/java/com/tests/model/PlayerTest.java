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

}
