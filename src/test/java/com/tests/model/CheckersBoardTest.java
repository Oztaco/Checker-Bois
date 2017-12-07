package com.tests.model;

import com.webcheckers.model.gamelogic.CheckersBoard;
import com.webcheckers.Exceptions.InvalidMoveException;
import com.webcheckers.model.gamelogic.Player;
import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.*; //TODO

/**
 * Unit tests for the CheckerBoard class.
 * Primarily tests Move and Attack, as they are the most
 * complex methods, and have a lot of aspects that require
 * thorough testing.
 *
 * @author <a href='mailto:jrh4099@rit.edu'>Jonathan Hubbard</a>
 */

//<<<<<<< Updated upstream //TODO
public class CheckersBoardTest {
    @Test
    public void testBoardConstructor(){
        new CheckersBoard(new Player("123","player1"),(new Player("123","player2")));
    }

    @Test(expected = InvalidMoveException.class)
    public void testMoveNotRightPlayer() throws InvalidMoveException {
        CheckersBoard cb2 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb2.initBoard();
        cb2.move(1, 2, 0, 3, cb2.getPlayer(2));
    }

    @Test
    public void testValidMove() throws InvalidMoveException {
        CheckersBoard cb1 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb1.initBoard();
        cb1.move(1,2, 0,3, cb1.getPlayer(1));
    }

    @Test
    public void testGetPlayer(){
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.getPlayer(1);
    }

    @Test
    public void testGetBoardArrayValues(){
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        cb.getBoardArrayValues();
    }

    @Test
    public void testGetXYBoardArrayValues(){
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        cb.getXYBoardArrayValues();
    }

    @Test
    public void testGetPlayer1Board(){
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        cb.getPlayer1Board();
    }

    @Test
    public void testGetPlayer2Board(){
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        cb.getPlayer2Board();
    }

    @Test
    public void testMove1() throws InvalidMoveException{
        CheckersBoard cb1 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove2() throws InvalidMoveException{
        CheckersBoard cb2 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove3() throws InvalidMoveException{
        CheckersBoard cb3 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove4() throws InvalidMoveException{
        CheckersBoard cb4 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove5() throws InvalidMoveException{
        CheckersBoard cb5 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove6() throws InvalidMoveException{
        CheckersBoard cb6 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testMove7() throws InvalidMoveException{
        CheckersBoard cb7 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
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

    @Test
    public void testKing1() throws InvalidMoveException {
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 8-----------------------");
        System.out.println("Test 8: Player 1 kings a piece");
        cb.emptyBoard();
        cb.putPiece(1, 6, CheckersBoard.space.PLAYER1);
        cb.printBoard();
        try {
            cb.move(1, 6, 0, 7, cb.getPlayer(1));
            cb.printBoard();
            System.out.println("STATUS: PASSED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS:FAILED");
        }
    }

    @Test
    public void testKing2() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 9-----------------------");
        System.out.println("Test 9: Player 1 moves a king forwards");
        cb.emptyBoard();
        cb.putPiece(1,6, CheckersBoard.space.PLAYER1KING);
        cb.printBoard();
        try{
            cb.move(1,6,0,7,cb.getPlayer(1));
            cb.printBoard();
            System.out.println("STATUS: PASSED");
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:FAILED");
        }
    }

    @Test
    public void testKing3() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 10-----------------------");
        System.out.println("Test 10: Player 1 moves a king backwards");
        cb.emptyBoard();
        cb.putPiece(1,6, CheckersBoard.space.PLAYER1KING);
        cb.printBoard();
        try{
            cb.move(1,6,0,5,cb.getPlayer(1));
            cb.printBoard();
            System.out.println("STATUS: PASSED");
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:FAILED");
        }
    }

    @Test
    public void testAttack1() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 11-----------------------");
        System.out.println("Test 11: Player 1 makes an attack");
        cb.emptyBoard();
        cb.putPiece(3,4, CheckersBoard.space.PLAYER1);
        cb.putPiece(4,5, CheckersBoard.space.PLAYER2);
        cb.printBoard();
        try{
            cb.attack(3,4,5,6,cb.getPlayer(1));
            cb.printBoard();
            System.out.println("STATUS: PASSED");
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:FAILED");
        }
    }

    @Test
    public void testAttack2() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 12-----------------------");
        System.out.println("Test 12: Player 2 makes an attack");
        cb.emptyBoard();
        cb.putPiece(3,4, CheckersBoard.space.PLAYER1);
        cb.putPiece(4,5, CheckersBoard.space.PLAYER2);
        cb.printBoard();
        try{
            cb.attack(4,5,2,3,cb.getPlayer(2));
            cb.printBoard();
            System.out.println("STATUS: PASSED");
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:FAILED");
        }
    }

    @Test
    public void testAttack3() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 13-----------------------");
        System.out.println("Test 13: Player 2 makes an attack with player 1's piece");
        cb.emptyBoard();
        cb.putPiece(3,4, CheckersBoard.space.PLAYER1);
        cb.putPiece(4,5, CheckersBoard.space.PLAYER2);
        cb.printBoard();
        try {
            cb.attack(3, 4, 5, 6, cb.getPlayer(2));
            cb.printBoard();
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }

    @Test
    public void testAttack4() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 14-----------------------");
        System.out.println("Test 14: Player 1 makes an attack into an occupied space");
        cb.emptyBoard();
        cb.putPiece(3,4, CheckersBoard.space.PLAYER1);
        cb.putPiece(4,5, CheckersBoard.space.PLAYER2);
        cb.putPiece(5,6, CheckersBoard.space.PLAYER2);
        cb.printBoard();
        try{
            cb.attack(3,4,5,6,cb.getPlayer(2));
            cb.printBoard();
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }

    @Test
    public void testAttack5() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 15-----------------------");
        System.out.println("Test 15: Player 1 makes an attack over an empty space");
        cb.emptyBoard();
        cb.putPiece(3,4, CheckersBoard.space.PLAYER1);
        cb.printBoard();
        try{
            cb.attack(3,4,5,6,cb.getPlayer(2));
            cb.printBoard();
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }

    @Test
    public void testAttack6() throws InvalidMoveException{
        CheckersBoard cb = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb.initBoard();
        System.out.println("\n---------------------BOARD 16-----------------------");
        System.out.println("Test 16: Player 1 makes an attack into a space outside the board");
        cb.emptyBoard();
        cb.putPiece(3,6, CheckersBoard.space.PLAYER1);
        cb.putPiece(4,7, CheckersBoard.space.PLAYER2);
        cb.printBoard();
        try{
            cb.attack(3,6,5,8,cb.getPlayer(2));
            cb.printBoard();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }
    }

//=======
//>>>>>>> Stashed changes //TODO
    
}