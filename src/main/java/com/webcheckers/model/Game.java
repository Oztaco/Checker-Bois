package com.webcheckers.model;
import com.webcheckers.model.CheckersBoard;
import com.webcheckers.model.Player;


public class Game {
    private CheckersBoard board;
    private Player player1;
    private Player player2;
    private String id;
    private int playerTurn;


/*  #######################################################################################################
    Constructor
    #######################################################################################################*/
    public Game(Player p1, Player p2, String id){
        this.player1 = p1;
        this.player2 = p2;
        this.id = id;
        this.board = new CheckersBoard(p1,p2);
        this.board.initBoard();
        this.playerTurn = 1;
    }

/*  #######################################################################################################
    Getter Methods
    #######################################################################################################*/
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


/*  #######################################################################################################
    Public Methods
    #######################################################################################################*/
    /**
     * -----------------------------------------------------------------------------------------------------
     * getGameBoardAsString(int playerNum)
     * 
     * Gets a JSON representation of the whole Game for sending to UI formatted to the perspective of
     * a player represented by an int
     * @return String JSONFill
     * ----------------------------------------------------------------------------------------------------
     */
    public String getGameBoardAsString(int playerNum){
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
     * ----------------------------------------------------------------------------------------------------
     * getSimpleGameAsString()
     * 
     * Returns String representation of the Game for the JSON file.
     * @return
     * ----------------------------------------------------------------------------------------------------
     */
    public String getSimpleGameAsString(){
        String game = "{\"id\": \"" + this.id + "\", \"playerOneUsername\": \"" + this.player1.getName() + "\", \"playerTwoUsername\": \"" + this.player2.getName() + "\"}";
        return game;
    }

    public static void main(String args[]){
        Player p1 = new Player("123","Peepee");
        Player p2 = new Player("123","PooPoo");
        Game g = new Game(p1, p2, "GAME_ID");
        System.out.print(g.getSimpleGameAsString());
    }
}
