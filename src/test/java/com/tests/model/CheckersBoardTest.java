package com.tests.model;

import com.webcheckers.appl.PlayerSession;
import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.InvalidMoveException;
import com.webcheckers.model.Player;
import org.junit.Test;

import static org.mockito.Mockito.mock;

//import static org.junit.jupiter.api.Assertions.*; //TODO

//<<<<<<< Updated upstream //TODO
class CheckersBoardTest {
    @Test
    private void testBoardConstructor(){
        new CheckersBoard(new Player("player1"),(new Player("player2")));
    }

    @Test
    private void testPlayerSessionConstructor(){
        new PlayerSession("username","1234");
    }

    @Test(expected = InvalidMoveException.class)
    private void testMoveNotRightPlayer() throws InvalidMoveException {
        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        cb2.printBoard();
        cb2.move(1, 2, 0, 3, cb2.getPlayer(2), CheckersBoard.moveType.move);
    }

    @Test
    private void testValidMove() throws InvalidMoveException {
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        cb1.printBoard();
        cb1.move(1,2, 0,3, cb1.getPlayer(1), CheckersBoard.moveType.move);
    }
//=======
//>>>>>>> Stashed changes //TODO
    
}