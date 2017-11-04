package com.webcheckers.model;

import com.webcheckers.model.Exceptions.GameNotAddedException;
import com.webcheckers.model.Exceptions.PlayerNotAddedException;

import java.util.ArrayList;

public class Lobby {
    private ArrayList<Player> players;
    private ArrayList<CheckersBoard> games;

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

    public static void main(String args[]){
        //TODO MAKE SOME TESTS
    }
}
