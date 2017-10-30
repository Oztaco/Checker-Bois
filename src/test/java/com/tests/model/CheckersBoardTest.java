package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.InvalidMoveException;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersBoardTest {
    @Test(expected = InvalidMoveException.class)
    private void testMoveNotRightPlayer() {
        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        cb2.printBoard();
        cb2.move(1,2, 0,3, cb2.getPlayer(2), CheckersBoard.moveType.move);
    }
    @Test
    private void testValidMove(){
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        cb1.printBoard();
        cb1.move(1,2, 0,3, cb1.getPlayer(1), CheckersBoard.moveType.move);
    }
}