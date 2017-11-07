/**
 *  Defines the frontend model for a game board
 */


/*
*   Constructor for a checkerboard
*   @return {Array} checker board 2D array
*/
CheckersBoard = function () {
    this.data = [];
    for (var col = 0; col < 8; col++) {
        var newCol = [];
        for (var row = 0; row < 8; row++) {
            newRow.push(BOARD_SPACE.NULL);
        }
        this.data.push(newCol);
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
    return this.data[x][y];
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
        this.data[x][y] = piece;
    }
    else {
        console.log("CheckersBoard setPieceAt error: Incorrect inputs.");
    }
}


/*
*   Downloads the board configuration JSON for the given
*   gameID
*   @param {String} gameID
*/
CheckersBoard.prototype.downloadBoard = function (gameID) {
    // TODO
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