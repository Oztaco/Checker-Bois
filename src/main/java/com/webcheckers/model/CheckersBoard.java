package com.webcheckers.model;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.webcheckers.model.Exceptions.InvalidMoveException;

public class CheckersBoard {

    public enum space{
        EMPTY,
        INVALID,
        PLAYER1,
        PLAYER2,
        PLAYER1KING,
        PLAYER2KING;
    }
    private space[][] board;
    private Player player1;
    private Player player2;


/*  #######################################################################################################
    Constructor Methods
    #######################################################################################################*/
    /**
     * ------------------------------------------------------------------------------------------------------
     * CheckersBoard()
     *
     * Constructor for a board.  Uses two players as input
     * @param username1
     * @param username2
     * ------------------------------------------------------------------------------------------------------
     */
    public CheckersBoard(Player username1, Player username2){
        this.board = new space[8][8];
        player1 = username1;
        player2 = username2;
    }

    /**
     * ------------------------------------------------------------------------------------------------------
     * initBoard()
     *
     * Initializes the board for a valid game of checkers, filling it with enums corresponding to the
     * contents of the spaces.
     * ------------------------------------------------------------------------------------------------------
     */
    public void initBoard(){
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                //player 1 side
                if(y == 0 || y == 2){
                    if(x % 2 == 0){
                        setCoords(x, y, space.INVALID);
                    }
                    else{
                        setCoords(x, y, space.PLAYER1);
                    }
                }
                else if(y == 1){
                    if(x % 2 == 0){
                        setCoords(x,y,space.PLAYER1);
                    }
                    else{
                        setCoords(x,y,space.INVALID);
                    }
                }

                //middle area
                else if(y == 3){
                    if(x % 2 == 0){
                        setCoords(x,y,space.EMPTY);
                    }
                    else{
                        setCoords(x,y,space.INVALID);
                    }
                }
                else if(y == 4){
                    if(x % 2 == 0){
                        setCoords(x,y,space.INVALID);
                    }
                    else{
                        setCoords(x,y,space.EMPTY);
                    }
                }

                //player 2 side
                if(y == 5 || y == 7){
                    if(x % 2 == 0){
                        setCoords(x,y,space.PLAYER2);
                    }
                    else{
                        setCoords(x,y,space.INVALID);
                    }
                }
                else if (y==6){   //y == 6
                    if(x % 2 == 0){
                        setCoords(x,y,space.INVALID);
                    }
                    else{
                        setCoords(x,y,space.PLAYER2);
                    }
                }
            }
        }
    }


/*  #######################################################################################################
    Getter Methods
    #######################################################################################################*/

    /**
     * ------------------------------------------------------------------------------------------------------
     * getPlayer(int player)
     *
     * @param player - int representation of player
     * @return p - Player that corresponds to that Number player
     * ------------------------------------------------------------------------------------------------------
     */
    public Player getPlayer(int player){
        Player p = null;
        if(player == 1){
            p = player1;
        }
        else if(player == 2){
            p = player2;
        }
        return p;
    }

    /**
     * ------------------------------------------------------------------------------------------------------
     * getBoardArrayValues()
     *
     * Creates a copy of the 2D array that stores the checkersboard and then
     * returns that new array.
     * The array is indexed by Array[y][x]
     * @return 2D array[y][x]
     * ------------------------------------------------------------------------------------------------------
     */
    public space[][] getBoardArrayValues() {
        space[][] newArray = new space[this.board.length][];
        for (int i = 0; i < this.board.length; i++) {
            newArray[i] = this.board[i].clone();
        }
        return newArray;
    }

    /**
     * ------------------------------------------------------------------------------------------------------
     * getXYBoardArrayValues()
     *
     * Creates a copy of the 2D array that stores the checkboard, then flips it
     * so that you can access index it by Array[x][y]
     * @return 2D array[x][y]
     * ------------------------------------------------------------------------------------------------------
     */
    public space[][] getXYBoardArrayValues() {
        space[][] newArray = new space[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                newArray[x][y] = this.getCoords(x,y);
            }
        }
        return newArray;
    }

    /**
     * ----------------------------------------------------------------------------------------------------
     * getPlayer1Board()
     *
     * Returns a 2D array indexed by [x][y] representing the board with Player 1
     * on the bottom of the array
     * ----------------------------------------------------------------------------------------------------
     */
    public space[][] getPlayer1Board() {
        return getBoardArrayValues();
        // space[][] newArray = getBoardArrayValues();
        // for (int x = 0; x < 8 ; x++) {
        //     for (int y = 0; y < 8 / 2; y++) {
        //         space val = this.getCoords(x,y);
        //         newArray[x][y] = newArray[x][7 - y];
        //         newArray[x][7 - y] = val;
        //     }
        // }
        // return newArray;
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * getPlayer2Board()
     *
     * Returns a 2D array indexed by [x][y] representing the board with Player 2
     * on the bottom of the array
     * -----------------------------------------------------------------------------------------------------
     */
    public space[][] getPlayer2Board() {
        return getBoardArrayValues();
    }

    /**
     * -----------------------------------------------------------------------------------------------------
     * setCoords
     *
     * sets the coordinates to a certain type (Nice!)
     * @param x
     * @param y
     * @param newSpace
     * -----------------------------------------------------------------------------------------------------
     */
    public void setCoords(int x, int y, space newSpace){
        this.board[x][y] = newSpace;
    }


    public space getCoords(int x, int y){
        return this.board[x][y];
    }

    public space getCoords(space[][] board2, int x, int y){
        return board2[x][y];
    }


/*  #######################################################################################################
    Public Methods
    #######################################################################################################*/
    /**
     * ------------------------------------------------------------------------------------------------------
     * kingPiece()
     *
     * Kings the piece at x,y if it is not already a king.  Does nothing otherwise.
     * @param x
     * @param y
     * -------------------------------------------------------------------------------------------------------
     */
    public void kingPiece(int x, int y){
        if(this.board[y][x] == space.PLAYER1){
            this.board[y][x] = space.PLAYER1KING;
        }
        else if(this.board[y][x] == space.PLAYER2) {
            this.board[y][x] = space.PLAYER2KING;
        }
    }

    /**
     * --------------------------------------------------------------------------------------------------------
     * movePiece(int x0, int y0, int x1, int y1, Player player)
     *
     * Moves a piece for the player specified.
     *
     * Checks:
     *      Whether the piece moved belongs to the correct player
     *      Whether the piece moved
     *
     * @param x0 - initial x position
     * @param y0 - initial y position
     * @param x1 - new x position
     * @param y1 - new y position
     * @param player - player making the move
     * ---------------------------------------------------------------------------------------------------------
     */

    public void move(int x0, int y0, int x1, int y1, Player player) throws InvalidMoveException {

        //Setup changInX and changeInY for determining validity later
        int changeInX = x1-x0;
        int changeInY = y1-y0;
        space me = null;

        //Set me and meKing to the current Player Enums
        if(player.equals(this.player1)){
            if(getCoords(x0,y0) == space.PLAYER1KING){
                me = space.PLAYER1KING;
            }
            else{
                me = space.PLAYER1;
            }
        }

        else if(player.equals(this.player2)){
            if(getCoords(x0,y0) == space.PLAYER2KING){
                me = space.PLAYER2KING;
            }
            else{
                me = space.PLAYER2;
            }
        }

        //Throw exception if a player tries to move the piece of the other player
        if(!(getCoords(x0,y0) == me)){
            throw new InvalidMoveException("The contents of the tile do not match the player trying to play");
        }

        //Throw exception if Moves have a distance of 1 from the original space
        if(Math.abs(changeInX) != 1 || Math.abs(changeInY) != 1) {
            throw new InvalidMoveException("Moves must be a distance of 1 from the piece");
        }

        else {
            if(me == space.PLAYER1KING){                //If space has a Player 1 King on tile, register move
                if (getCoords(x1,y1) == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = getCoords(x0,y0);
                    setCoords(x0,y0,space.EMPTY);
                    setCoords(x1,y1,tempSpace);
                }
                else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER2KING){           //If space has a Player 2 King on tile, register move
                if (getCoords(x1,y1) == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = getCoords(x0,y0);
                    setCoords(x0,y0,space.EMPTY);
                    setCoords(x1,y1,tempSpace);
                }
                else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER1){               //If space has a Player 1 on tile, register move
                if(changeInY > 0){                      //if player is moving in the appropriate direction
                    if (getCoords(x1,y1) == space.EMPTY) { //and if space if not occupied
                        space tempSpace = getCoords(x0,y0);
                        setCoords(x0,y0,space.EMPTY);
                        setCoords(x1,y1,tempSpace);
                    }
                    else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
            else if(me == space.PLAYER2){               //If space has a Player 2 on tile, register move
                if(changeInY < 0){                      //if player is moving in the appropriate direction
                    if (getCoords(x1,y1) == space.EMPTY) { //and if space if not occupied
                        space tempSpace = getCoords(x0,y0);
                        setCoords(x0,y0,space.EMPTY);
                        setCoords(x1,y1,tempSpace);
                    }
                    else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
        }

        //Check if any players should be kinged after a move
        for(int x = 0; x < 8; x++){
            if(getCoords(x,0) == space.PLAYER2){
                kingPiece(x,0);     //King piece located at
            }
            else if(getCoords(x,7) == space.PLAYER1){
                kingPiece(x,7);     //King piece located at
            }
        }
    }

    /**
     * uncheckedMove
     *
     * Moves a piece without checking validity, used for reverting moves and testing
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param player
     */
    public void uncheckedMove(int x0, int y0, int x1, int y1, Player player){
        space tempSpace = this.board[y0][x0];
        this.board[y0][x0] = space.EMPTY;
        this.board[y1][x1] = tempSpace;
    }


  
    /**
     * -----------------------------------------------------------------------------------------------------
     * attack(int x0, int y0, int x1, int y1, Player player)
     *
     * Performs an attack, where the current player's
     * piece leaps over an enemy piece, which is then
     * removed from the board.
     *
     * //TODO REDO FOR NEW STYLE
     *
     * @param x0 - initial x position
     * @param y0 - initial y position
     * @param x1 - final x position
     * @param y1 - final y position
     * @param player - player making the move
     * ------------------------------------------------------------------------------------------------------
     */
    public void attack(int x0, int y0, int x1, int y1, Player player) throws InvalidMoveException{
        //Setup changInX and changeInY for determining validity later
        int changeInX = x1-x0;
        int changeInY = y1-y0;
        space me = null;

        //Set me and meKing to the current Player Enums
        if(player.equals(this.player1)){
            if(getCoords(x0,y0) == space.PLAYER1KING){
                me = space.PLAYER1KING;
            }
            else{
                me = space.PLAYER1;
            }
        }

        else if(player.equals(this.player2)){
            if(getCoords(x0,y0) == space.PLAYER2KING){
                me = space.PLAYER2KING;
            }
            else{
                me = space.PLAYER2;
            }
        }

        //Throw exception if a player tries to move the piece of the other player
        if(!(getCoords(x0,y0) == me)){
            throw new InvalidMoveException("The contents of the tile do not match the player trying to play");
        }

        //Throw exception if Moves have a distance of 1 from the original space
        if(Math.abs(changeInX) != 2 || Math.abs(changeInY) != 2) {
            throw new InvalidMoveException("Attacks must be a distance of 1 from the piece");
        }

        else {
            if(me == space.PLAYER1KING){                //If space has a Player 1 King on tile, register move
                if (getCoords(x1,y1) == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = getCoords(x0,y0);
                    setCoords(x0,y0,space.EMPTY);
                    setCoords(x1,y1,tempSpace);

                    //Handle Deletion of other player's piece
                    if(changeInX > 0 && changeInY > 0){
                        setCoords(x0+1, y0+1, space.EMPTY);
                    }
                    else if(changeInX > 0 && changeInY < 0){
                        setCoords(x0+1, y0-1, space.EMPTY);
                    }
                    else if(changeInX < 0 && changeInY > 0){
                        setCoords(x0-1, y0+1, space.EMPTY);
                    }
                    else if(changeInX < 0 && changeInY < 0){
                        setCoords(x0-1, y0-1, space.EMPTY);
                    }
                }
                else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER2KING){           //If space has a Player 2 King on tile, register move
                if (getCoords(x1,y1) == space.EMPTY) {     //if space is not occupied, exception otherwise
                    space tempSpace = getCoords(x0,y0);
                    setCoords(x0,y0,space.EMPTY);
                    setCoords(x1,y1,tempSpace);

                    //Handle Deletion of other player's piece
                    if(changeInX > 0 && changeInY > 0){
                        setCoords(x0+1, y0+1, space.EMPTY);
                    }
                    else if(changeInX > 0 && changeInY < 0){
                        setCoords(x0+1, y0-1, space.EMPTY);
                    }
                    else if(changeInX < 0 && changeInY > 0){
                        setCoords(x0-1, y0+1, space.EMPTY);
                    }
                    else if(changeInX < 0 && changeInY < 0){
                        setCoords(x0-1, y0-1, space.EMPTY);
                    }
                }
                else {
                    throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                }
            }
            else if(me == space.PLAYER1){               //If space has a Player 1 on tile, register move
                if(changeInY > 0){                      //if player is moving in the appropriate direction
                    if (getCoords(x1,y1) == space.EMPTY) { //and if space if not occupied
                        space tempSpace = getCoords(x0,y0);
                        setCoords(x0,y0,space.EMPTY);
                        setCoords(x1,y1,tempSpace);

                        //Handle Deletion of other player's piece
                        if(changeInX > 0 && changeInY > 0){
                            setCoords(x0+1, y0+1, space.EMPTY);
                        }
                        else if(changeInX > 0 && changeInY < 0){
                            setCoords(x0+1, y0-1, space.EMPTY);
                        }
                        else if(changeInX < 0 && changeInY > 0){
                            setCoords(x0-1, y0+1, space.EMPTY);
                        }
                        else if(changeInX < 0 && changeInY < 0){
                            setCoords(x0-1, y0-1, space.EMPTY);
                        }
                    }
                    else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
            else if(me == space.PLAYER2){               //If space has a Player 2 on tile, register move
                if(changeInY < 0){                      //if player is moving in the appropriate direction
                    if (getCoords(x1,y1) == space.EMPTY) { //and if space if not occupied
                        space tempSpace = getCoords(x0,y0);
                        setCoords(x0,y0,space.EMPTY);
                        setCoords(x1,y1,tempSpace);

                        //Handle Deletion of other player's piece
                        if(changeInX > 0 && changeInY > 0){
                            setCoords(x0+1, y0+1, space.EMPTY);
                        }
                        else if(changeInX > 0 && changeInY < 0){
                            setCoords(x0+1, y0-1, space.EMPTY);
                        }
                        else if(changeInX < 0 && changeInY > 0){
                            setCoords(x0-1, y0+1, space.EMPTY);
                        }
                        else if(changeInX < 0 && changeInY < 0){
                            setCoords(x0-1, y0-1, space.EMPTY);
                        }
                    }
                    else {
                        throw new InvalidMoveException("Space you want to move to is Occupied or Invalid");
                    }
                }
                else {
                    throw new InvalidMoveException("Only kings can move backwards");
                }
            }
        }

        //Check if any players should be kinged after a move
        for(int x = 0; x < 8; x++){
            if(getCoords(x,0) == space.PLAYER2){
                kingPiece(x,0);     //King piece located at
            }
            else if(getCoords(x,7) == space.PLAYER1){
                kingPiece(x,7);     //King piece located at
            }
        }
    }


/*  #######################################################################################################
    Testing Methods
    #######################################################################################################*/
    /**
     * -----------------------------------------------------------------------------------------------------
     * putPiece(int x, int y, space space)
     *
     * Puts a piece directly on the board.
     * For testing purposes only.
     * ------------------------------------------------------------------------------------------------------
     */
    public void putPiece(int x, int y, space space){
        board[x][y] = space;
    }

    /**
     * -------------------------------------------------------------------------------------------------------
     * emptyBoard()
     *
     * Empties the entire board.
     * For testing purposes only.
     * -------------------------------------------------------------------------------------------------------
     */
    public void emptyBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = space.EMPTY;
            }
        }
    }

    /**
     * ----------------------------------------------------------------------------------------------------------
     * printBoard()
     *
     * Prints the board for testing purposes
     * ----------------------------------------------------------------------------------------------------------
     */
    public void printBoard(){
        System.out.println("");
        String val;

        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                val = "U";
                if(getCoords(x,y) == space.INVALID){
                    val = ".";
                }
                else if(getCoords(x,y) == space.EMPTY){
                    val = " ";
                }
                else if(getCoords(x,y) == space.PLAYER1){
                    val = "1";
                }
                else if(getCoords(x,y) == space.PLAYER2){
                    val = "2";
                }
                else if(getCoords(x,y) == space.PLAYER1KING){
                    val = "A";
                }
                else if(getCoords(x,y) == space.PLAYER2KING){
                    val = "B";
                }
                System.out.print("[" + val + "]");
            }
            System.out.println("");
        }
    }

    public void printArray(space[][] board){
        System.out.println("");
        String val;

        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                val = "U";
                if(getCoords(board,x,y) == space.INVALID){
                    val = ".";
                }
                else if(getCoords(board,x,y) == space.EMPTY){
                    val = " ";
                }
                else if(getCoords(board,x,y) == space.PLAYER1){
                    val = "1";
                }
                else if(getCoords(board,x,y) == space.PLAYER2){
                    val = "2";
                }
                else if(getCoords(board,x,y) == space.PLAYER1KING){
                    val = "A";
                }
                else if(getCoords(board,x,y) == space.PLAYER2KING){
                    val = "B";
                }
                System.out.print("[" + val + "]");
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) throws InvalidMoveException {

        CheckersBoard c = new CheckersBoard(new Player("123","Frank"), new Player("456","Dan"));
        c.initBoard();
        c.printBoard();
        System.out.println("(0,1) contains: " + c.getCoords(0,1));
        System.out.println("(0,3) contains: " + c.getCoords(0,3));
        c.printArray(c.getPlayer2Board());
        c.printArray(c.getPlayer1Board());

        Player p1 = new Player("123","Frank");
        Player p2 = new Player("456","Dan");
        CheckersBoard c1 = new CheckersBoard(p1, p2);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                c1.putPiece(i,j,space.EMPTY);
            }
        }
        c1.putPiece(2,1, space.PLAYER1);
        c1.putPiece(3,2, space.PLAYER2);
        c1.printBoard();
        c1.attack(3,2, 1,0,p2);
        c1.printBoard();

        //-------------------------------------------------------------------------------------------------------------
        /**
         * MOVE TESTS
         *      Tests Move method
         *          1.1     Tests valid move player 1
         *          1.2     Tests Backwards Move player 1 (InvalidMoveException)
         *          2       Tests move when wrong player is called (InvalidMoveException)
         *          3       Tests move when the player tries to move an invalid distance (InvalidMoveException)
         *          4       Tests move into an occupied space (InvalidMoveException)
         *          5       Tests move into an invalid space (InvalidMoveException)
         *          6.1     Valid Move Player 2
         *          6.2     Backwards move Player 2 (Invalid Move Exception)
         *          7       Invalid Move Player 2 (InvalidMoveException)
         *          //TODO  ADD TESTS FOR:
         *              Valid Move Player1King
         *              Valid Move Player2King
         *              Move that kings Player 1
         *              Move that kings Player 2
         */

        /*
        //TESTS 1
        //-------------------------------------------------------------------------------------------------------------

        CheckersBoard cb1 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb1.initBoard();
        System.out.println("---------------------BOARD 1---------------------");
        System.out.println("Test 1.1: Player 1 makes a Valid Move");
        cb1.printBoard();
        try {
            cb1.move(1, 2, 0, 3, cb1.getPlayer(1));
            System.out.println("STATUS: PASSED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");
        }


        System.out.println("\nTest 1.2: Player 1 Moves Backwards");
        cb1.printBoard();
        try {
            cb1.move(0, 3, 1, 2, cb1.getPlayer(1));
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 2
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb2 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb2.initBoard();
        System.out.println("\n---------------------BOARD 2---------------------");
        System.out.println("Test 2: Throws Error when player != player at tile");
        cb2.printBoard();
        try {
            cb2.move(1, 2, 0, 3, cb2.player2);
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 3
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb3 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb3.initBoard();
        System.out.println("\n---------------------BOARD 3---------------------");
        System.out.println("Test 3: Throws Error when distance to next move > 1");
        cb3.printBoard();
        try {
            cb3.move(1, 2, 1, 4, cb3.player1);
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }

        //TESTS 4
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb4 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb4.initBoard();
        System.out.println("\n---------------------BOARD 4---------------------");
        System.out.println("Test 4: Throws Error when space is occupied");
        cb4.printBoard();
        try {
            cb4.move(1, 0, 0, 1, cb4.player1);
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 5
        //--------------------------------------------------------------------------------------------------------------


        CheckersBoard cb5 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb5.initBoard();
        System.out.println("\n---------------------BOARD 5---------------------");
        System.out.println("Test 5: Throws Error when space is invalid");
        cb5.printBoard();
        try {
            cb5.move(1, 0, 1, 1, cb5.player1);
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 6
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb6 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb6.initBoard();
        System.out.println("\n---------------------BOARD 6---------------------");
        System.out.println("Test 6.1: Player 2 Makes A Valid Move");
        cb6.printBoard();
        try {
            cb6.move(0, 5, 1, 4, cb6.getPlayer(2));
            System.out.println("STATUS: PASSED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: FAILED");
        }

        System.out.println("Test 6.2: Player 2 Moves Backwards");
        cb6.printBoard();
        try {
            cb6.move(1, 4, 0, 5, cb6.getPlayer(2));
            System.out.println("STATUS: FAILED");
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS: PASSED");
        }

        //TESTS 7
        //--------------------------------------------------------------------------------------------------------------

        CheckersBoard cb7 = new CheckersBoard(new Player("123","Fluffy"), new Player("123","Fatty"));
        cb7.initBoard();
        System.out.println("\n---------------------BOARD 7---------------------");
        System.out.println("Test 7: Player 2 makes an invalid move");
        cb7.printBoard();
        try {
            cb7.move(0, 5, 0, 4, cb7.getPlayer(2));
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage() + "\nSTATUS:PASSED");
        }


        //-------------------------------------------------------------------------------------------------------------
        */
    }
}
