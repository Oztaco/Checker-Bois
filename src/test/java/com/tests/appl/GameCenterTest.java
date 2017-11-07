package com.tests.appl;

import com.webcheckers.appl.GameCenter;
import org.junit.Test;

public class GameCenterTest {

    @Test
    public void testAddSession(){
        GameCenter gc = new GameCenter();
        gc.addSession("test", "1337");
    }

    @Test
    public void testGetSession(){
        GameCenter gc = new GameCenter();
        gc.addSession("test", "1337");
        if(gc.getSessionByIp("1337") == null){
            System.out.println("ERROR: IP 1337 not found in GameCenter");
        }
    }
}
