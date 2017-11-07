package com.tests.model;

import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.Exceptions.InvalidMoveException;
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
        cb2.move(1, 2, 0, 3, cb2.getPlayer(2));
    }

    @Test
    public void testValidMove() throws InvalidMoveException {
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        cb1.move(1,2, 0,3, cb1.getPlayer(1));
    }

    public void testMove1() throws InvalidMoveException{
        CheckersBoard cb1 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb1.initBoard();
        System.out.println("---------------------BOARD 1---------------------");
        System.out.println("Test 1.1: Player 1 makes a Valid Move");
        cb1.printBoard();
        try{
            cb1.move(1,2, 0,3, cb1.getPlayer(1));
            System.out.println("STATUS: PASSED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");

        }

        System.out.println("\nTest 1.2: Player 1 Moves Backwards");
        cb1.printBoard();
        try{
            cb1.move(0,3,1,2,cb1.getPlayer(1));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }
    }

    public void testMove2() throws InvalidMoveException{
        CheckersBoard cb2 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb2.initBoard();
        System.out.println("\n---------------------BOARD 2---------------------");
        System.out.println("Test 2: Throws Error when player != player at tile");
        cb2.printBoard();
        try{
            cb2.move(1,2, 0,3, cb2.getPlayer(2));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }
    }

    public void testMove3() throws InvalidMoveException{
        CheckersBoard cb3 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb3.initBoard();
        System.out.println("\n---------------------BOARD 3---------------------");
        System.out.println("Test 3: Throws Error when distance to next move > 1");
        cb3.printBoard();
        try{
            cb3.move(1,2, 1,4, cb3.getPlayer(1));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }

    public void testMove4() throws InvalidMoveException{
        CheckersBoard cb4 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb4.initBoard();
        System.out.println("\n---------------------BOARD 4---------------------");
        System.out.println("Test 4: Throws Error when space is occupied");
        cb4.printBoard();
        try{
            cb4.move(1,0, 0,1, cb4.getPlayer(1));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }
    }

    public void testMove5() throws InvalidMoveException{
        CheckersBoard cb5 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb5.initBoard();
        System.out.println("\n---------------------BOARD 5---------------------");
        System.out.println("Test 5: Throws Error when space is invalid");
        cb5.printBoard();
        try{
            cb5.move(1,0, 1,1, cb5.getPlayer(1));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }
    }

    public void testMove6() throws InvalidMoveException{
        CheckersBoard cb6 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb6.initBoard();
        System.out.println("\n---------------------BOARD 6---------------------");
        System.out.println("Test 6.1: Player 2 Makes A Valid Move");
        cb6.printBoard();
        try{
            cb6.move(0,5, 1,4, cb6.getPlayer(2));
            System.out.println("STATUS: PASSED");
        }
        catch(InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");
        }

        System.out.println("Test 6.2: Player 2 Moves Backwards");
        cb6.printBoard();
        try{
            cb6.move(1,4,0,5,cb6.getPlayer(2));
            System.out.println("STATUS: FAILED");
        }
        catch(InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }
    }

    public void testMove7() throws InvalidMoveException{
        CheckersBoard cb7 = new CheckersBoard(new Player("Fluffy"), new Player("Fatty"));
        cb7.initBoard();
        System.out.println("\n---------------------BOARD 7---------------------");
        System.out.println("Test 7: Player 2 makes an invalid move");
        cb7.printBoard();
        try{
            cb7.move(0,5,0,4,cb7.getPlayer(2));
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }
//=======
//>>>>>>> Stashed changes //TODO
    
}