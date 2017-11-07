/**
 *  Brings all the code together to make the functionality work
 */

PLAYER_1 = 1;
PLAYER_2 = 2;

// Enum for possible values of a space on the board
BOARD_SPACE = {
    NULL: 0,
    EMPTY: 1,
    PLAYER_1_PIECE: 2, // Player's self
    PLAYER_2_PIECE: 3, // Opponent
    PLAYER_1_KING: 4,
    PLAYER_2_KING: 5
}

// The state of the current loaded game
GAME_STATES = {
    NULL: 0,
    LOADING: 1,
    PLAYING: 2,
    SPECTATING: 3
}

// Holds the data for whichever game is currently loaded.
// Updates each time the game is changed
currentGame = {
    gameState: GAME_STATES.NULL,
    activePlayer: -1, // Whose turn is it?
    board: null // checkersBoard
}