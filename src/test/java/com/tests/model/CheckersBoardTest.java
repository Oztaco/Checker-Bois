package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.InvalidMoveException;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersBoardTest {
    @Test(expected = InvalidMoveException.class)
    private void move() {
        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        System.out.println("\n---------------------BOARD 2---------------------");
        System.out.println("Test2: Throws Error when player != player at tile");
        cb2.printBoard();
        try{
            cb2.move(1,2, 0,3, cb2.getPlayer(2), CheckersBoard.moveType.move);
        }
        catch(InvalidMoveException e){
            System.out.println("Error Caught: \"The contents of the tile do not match the player trying to play\"");
        }
    }

}