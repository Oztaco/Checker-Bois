package com.webcheckers.model;

import com.webcheckers.model.Exceptions.InvalidMoveException;
import com.webcheckers.model.Game;
import com.webcheckers.model.MoveType;
import com.webcheckers.model.Player;

public class PostMoveRouteTest {

    public static void testMove(){
        Player p1 = new Player("123", "Dan");
        Player p2 = new Player("456", "Cam");
        Game g = new Game(p1, p2, "123");

        try{
            g.playTurn(p1, 3, 2, 4, 3, MoveType.MOVE);
        }
        catch(InvalidMoveException e){

        }

        try{
            g.playTurn(p2, 4, 5, 3, 4, MoveType.MOVE);
        }
        catch(InvalidMoveException e){

        }

        try{
            g.playTurn(p1, 5, 2, 6, 3, MoveType.MOVE);
        }
        catch(InvalidMoveException e){

        }

        try{
            g.playTurn(p2, 6, 5, 7, 4, MoveType.MOVE);
        }
        catch(InvalidMoveException e){
            System.out.println("This should be an error");
        }

        try{
            g.playTurn(p2, 5, 6, 4, 5, MoveType.MOVE);
        }
        catch(InvalidMoveException e){
            System.out.println("This should be an error");
        }

        try{
            g.playTurn(p2, 3, 6, 4, 5, MoveType.MOVE);
        }
        catch(InvalidMoveException e){
            System.out.println("THis should return an error");
        }
    }

    public static void main(String[] args){
        testMove();
    }

}
