/**
 * This file handles the game states and animations
 */


/**
 * The scene that runs when a game is not currently being displayed to the
 * player
 */
scene("intro", {
	init: function () {

	},
	update: function () {
		// Check for spectating
		if (checkersBoard.player1_ID != gameState.id &&
			checkersBoard.player2_ID != gameState.id) {
			console.log("Spectating game");
			setScene("spectating");
		}
		// Check if it's my turn
		//else if ()
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
	},
	cleanUp: function () {

	}
});

/**
 * The scene that runs when it is the current player's turn, *before* the user
 * selects a piece to move
 */
scene("currentPlayerTurn", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

/**
 * The scene that runs when it is the opponent's turn. Should keep pinging the
 * server every 5 second to check for a move.
 */
scene("opponentTurn", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

/**
 * The scene that runs when it is the current player's turn and the player has
 * selected a specific piece to move. Should highlight possible moves
 */
scene("playerInput", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

/**
 * The scene that runs when a move is being animated from one space to another
 */
scene("animatingPiece", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

/**
 * TO DO: might remove later
 */
scene("pingingServer", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

scene("spectating", {
	init: function () {

	},
	update: function () {

	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
	},
	cleanUp: function () {

	}
});