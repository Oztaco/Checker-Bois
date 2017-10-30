package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.InvalidMoveException;
import com.webcheckers.model.Player;
import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.*; //TODO

//<<<<<<< Updated upstream //TODO
public class CheckersBoardTest {
    @Test
    public void testBoardConstructor(){
        new CheckersBoard(new Player("player1"),(new Player("player2")));
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveNotRightPlayer() throws InvalidMoveException {
        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        cb2.move(1, 2, 0, 3, cb2.getPlayer(2), CheckersBoard.moveType.move);
    }

    @Test
    public void testValidMove() throws InvalidMoveException {
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        cb1.move(1,2, 0,3, cb1.getPlayer(1), CheckersBoard.moveType.move);
    }
//=======
//>>>>>>> Stashed changes //TODO
    
}