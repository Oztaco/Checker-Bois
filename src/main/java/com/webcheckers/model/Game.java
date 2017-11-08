package com.webcheckers.model;

/**
 * This class is a representation of a Checkers Game.  It contains all of the data and logic for a game used in playing
 * a game of checkers.
 */
public class Game {
    private CheckersBoard board;
    private Player player1;
    private Player player2;
    private String id;
    private int playerTurn;


    //
    //CONSTRUCTOR
    //
    public Game(Player p1, Player p2, String id){
        this.player1 = p1;
        this.player2 = p2;
        this.id = id;
        this.board = new CheckersBoard(p1,p2);
        this.board.initBoard();
        this.playerTurn = 1;
    }

    //
    //GETTERS
    //
    public String getId(){
        return this.id;
    }
    public Player getPlayer(int player){
        if(player == 1){
            return this.player1;
        }
        else if(player == 2){
            return this.player2;
        }
        return null;
    }


    //
    //PUBLIC METHODS
    //
    /**
     * Gets a JSON representation of the whole Game for sending to UI
     * @return String JSONFill
     */
    public String getGameBoardJSON(int playerNum){
        String JSONFill = "";
        if(playerNum == 1){     //Player1 is requesting JSON
            //TODO FORMAT
        }
        else{                   //Player2 is requesting JSON
            //TODO FORMAT
        }
        return JSONFill;
    }

    /**
     * Returns String representation of the Game for the JSON file.
     * @return
     */
    public String getSimpleGameAsString(){
        //"id": "game1ewfqewfer","playerOneUsername": "Efe Ozturkoglu","playerTwoUsername": "Donald Knuth"
        String game = "\"id\": \"" + this.id + "\",\"playerOneUsername\": " + this.player1.getName() + "\",\"playerTwoUsername\": " + this.player2.getName() + "\"";
        return game;
    }
}
