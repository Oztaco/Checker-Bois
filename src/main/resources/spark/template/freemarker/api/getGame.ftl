{
    "activePlayer": 1, // 1 = Player 1's turn, 2 = Player 2's turn
    "spectating": false,
    "gameID": "aGkuYo",
    "player1Name": "Efe Ozturkoglu", // Player 1 is "myself"
    "player2Name": "Donald Knuth", // Player 2 is "my opponent"
    "board": 
        [  [3], [0], [3], [0], [3], [0], [3], [0],      // First ROW not column!!!
           [0], [3], [0], [3], [0], [3], [0], [3],      // each cell is equal to:
           [3], [0], [3], [0], [3], [0], [3], [0],      // 0: Space that cant be moved to
           [0], [0], [0], [0], [0], [0], [0], [0],      // 1: Empty space, but CAN be moved
           [0], [0], [0], [0], [0], [0], [0], [0],      // 2: Player 1 piece (player 1 is "myself")
           [0], [2], [0], [2], [0], [2], [2], [2],      // 3: Player 2 piece (player 2 is "my opponent")
           [2], [0], [2], [0], [2], [0], [2], [0],      // 4: Player 1 king
           [0], [2], [0], [2], [0], [2], [0], [2]   ]   // 5: Player 2 king
}