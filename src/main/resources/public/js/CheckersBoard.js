/**
 *  Defines the frontend model for a game board
 */


/*
*   Constructor for a checkerboard
*   @return {Array} checker board 2D array
*/
CheckersBoard = function () {
    this.board = [];
    this.activePlayer = -1;
    this.spectating = true;
    this.gameID = "-1";
    this.player1_ID = "-1";
    this.player2_ID = "-1";
    this.player1_Name = "Loading";
    this.player2_Name = "Loading";
    this.moves = [];
    for (var col = 0; col < 8; col++) {
        var newCol = [];
        for (var row = 0; row < 8; row++) {
            newCol.push(BOARD_SPACE.INVALID);
        }
        this.board.push(newCol);
    }
}


/*
*   Returns the enum value of whatever piece is at
*   the given location
*   @param {Number} x - (0 - 7)
*   @param {Number} y - (0 - 7)
*   @return {Number} enum value from BOARD_SPACE
*/
CheckersBoard.prototype.getPieceAt = function (x, y) {
    return this.board[x][y];
}


/*
*   Sets the value at (x, y) to the enum value
*   @param {Number} x - (0 - 7)
*   @param {Number} y - (0 - 7)
*   @param {Number} enum value from BOARD_SPACE
*/
CheckersBoard.prototype.setPieceAt = function (x, y, piece) {
    if (x >= 0 && x < 8 // Checks range of inputs
        && y >= 0 && y < 8
        && piece >= -1 && piece < 5) {
        this.board[x][y] = piece;
    }
    else {
        console.log("CheckersBoard setPieceAt error: Incorrect inputs.");
    }
}


/*
*   Downloads the board configuration JSON for the given gameID, and sets the
*   existing board configuration equal to it
*   @param {String} gameID
*/
CheckersBoard.prototype.downloadBoard = function () {
    getGame(function(response) {
        var responseJSON = JSON.parse(response);
        checkersBoard.activePlayer = responseJSON.activePlayer;
        checkersBoard.spectating = responseJSON.spectating;
        checkersBoard.player1_ID = responseJSON.player1_ID;
        checkersBoard.player2_ID = responseJSON.player2_ID;
        checkersBoard.player1_Name = responseJSON.player1_Name;
        checkersBoard.player2_Name = responseJSON.player2_Name;
        checkersBoard.board = responseJSON.board;
        checkersBoard.moves = responseJSON.moves;
    }, this.gameID);
}


/*
*   Returns which pieces player 1 can move anywhere.
*   @return {Array} Array of {x, y} objects
*/
CheckersBoard.prototype.getMoveablePieces = function () {
    // TODO
}


/*
*   Takes in a current valid space as input, and returns
*   {x, y} objects of where the piece can make valid moves to.
*   @return {Array} Array of {x, y} objects
*/
CheckersBoard.prototype.getPossibleMovesOfPiece = function (x, y) {
    // TODO
}