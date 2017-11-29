package com.tests.appl;

import com.webcheckers.appl.PlayerSession;
import org.junit.Test;

/**
 * Unit tests for the PlayerSession class.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

public class PlayerSessionTest {

    @Test
    public void testPlayerSessionConstructor(){
        new PlayerSession("username","1234");
    }
}
