package com.webcheckers.model;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players;
    private ArrayList<CheckersBoard> games;

    public Lobby(){
        this.games = new ArrayList<CheckersBoard>();
        this.players = new ArrayList<Player>();
    }

    /**
     * getPlayers()
     *
     * Getter for players
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    /**
     * getGames()
     *
     * Getter for games
     * @return ArrayList<CheckersBoard>
     */
    public ArrayList<CheckersBoard> getGames(){
        return this.games;
    }

    /**
     * addPlayer(Player player)
     *
     * adds a player specified to the lobby.
     * @param player
     * @throws PlayerNotAddedException
     */
    public void addPlayer(Player player) throws PlayerNotAddedException {
        try{
            this.players.add(player);
        }
        catch(Exception e){
            throw new PlayerNotAddedException("Player was not added to the Lobby");
        }
    }

    /**
     * addGame(CheckersBoard game)
     *
     * Adds game to list of games, given it's players are in the lobby.
     * @param game
     * @throws GameNotAddedException
     */
    public void addGame(CheckersBoard game) throws GameNotAddedException{
        Player player1 = game.getPlayer(1);
        Player player2 = game.getPlayer(2);

        if(this.players.contains(player1) && this.players.contains(player2)){
            this.games.add(game);
        }
        else{
            throw new GameNotAddedException("The players trying to play are not in the lobby yet");
        }
    }

    /**
     * Prints lobby for testing purposes
     */
    public void printLobby(){
        System.out.println("Lobby:");
        String players = "";
        for(int i = 0;i < this.players.size();i++){
            players += (this.players.get(i).getName() + ", " );
        }
        System.out.println("\tUnique players: " + players);
        System.out.println("\tNumber of unique games: " + this.games.size());
    }

    public static void main(String args[]){
        Lobby mainroom = new Lobby();
        System.out.println("TEST 1: PRINT EMPTY LOBBY");
        mainroom.printLobby();          //Should be empty

        Player fluffy = new Player("Fluffy");
        Player fatty = new Player("Fatty");

        try{
            System.out.println("\nTEST 2: ADD FLUFFY");
            mainroom.addPlayer(fluffy);
            mainroom.printLobby();
            System.out.println("STATUS: PASSED");
        } catch(PlayerNotAddedException e){
            System.out.println("STATUS: FAILED");
        }

        try{
            System.out.println("\nTEST 3: ADD FATTYY");
            mainroom.addPlayer(fatty);
            mainroom.printLobby();
            System.out.println("STATUS: PASSED");
        } catch(PlayerNotAddedException e){
            System.out.println("STATUS: FAILED");
        }

        try{
            System.out.println("\nTEST 3: ADD GAME WITH FLUFFY AND FATTY");
            mainroom.addGame(new CheckersBoard(fluffy, fatty));
            mainroom.printLobby();
            System.out.println("STATUS: PASSED");
        } catch (GameNotAddedException e) {
            System.out.println("STATUS: FAILED");
        }

    }
}
