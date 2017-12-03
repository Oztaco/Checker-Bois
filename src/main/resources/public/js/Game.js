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
		// Check for game over
		if (checkersBoard.playerWon != -1) {
			setScene("gameOver");
		}
		// Check for spectating
		if (checkersBoard.player1_ID != gameState.id &&
			checkersBoard.player2_ID != gameState.id) {
			console.log("Spectating game");
			setScene("spectating");
		}
		// Check if it's my turn
		else if (checkersBoard.isItPlayersTurn(gameState.id)) {
			setScene("currentPlayerTurn");
		}
		else {
			setScene("opponentTurn");
		}
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
		console.log("Current scene: currentPlayerTurn");
		mouseAlreadyPressed = false;
	},
	update: function () {
		if (keys.m)
			moveType = 1;
		else if (keys.a)
			moveType = 0;
		if (mouse.leftButton) {
			mouseAlreadyPressed = true;
			var mousepos = getMouseBoardCoords();
			x0 = mousepos.x;
			y0 = mousepos.y;
			if (x0 < 0 || x1 > 7)
				mouseAlreadyPressed = false;
			if (y0 < 0 || y1 > 7)
				mouseAlreadyPressed = false;
		}
		else if (mouseAlreadyPressed && !mouse.leftButton) {
			setScene("playerInput");
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
	},
	cleanUp: function () {

	}
});


x0 = -1;
y0 = -1;
x1 = -1;
y1 = -1;
moveType = 1; // 0 = attack, 1 = move
/**
 * The scene that runs when it is the current player's turn and the player has
 * selected a specific piece to move. Should highlight possible moves
 */
scene("playerInput", {
	init: function () {
		console.log("Current scene: playerInput");		
		x1 = -1;
		y1 = -1;
		mouseAlreadyPressed = false;
	},
	update: function () {
		if (keys.m)
			moveType = 1;
		else if (keys.a)
			moveType = 0;
		if (mouse.leftButton && !mouseAlreadyPressed) {
			var mousepos = getMouseBoardCoords();
			x1 = mousepos.x;
			y1 = mousepos.y;
			if (x0 == x1) {
				x1 = -1;
			}
			else {
				mouseAlreadyPressed = true;
				postMove(function(response) {
					console.log("Make move returned: " + response);
					setScene("pingingServer");				
				}, checkersBoard.gameID, moveType, x0, y0, x1, y1);
			}
		}
	},
	draw: function () {
		var highlights = [
			{x: x0, y: y0}
		];
		if (x1 != -1)
		highlights.push({x: x1, y: y1});
		renderBoard(DOM.canvas, checkersBoard, highlights);
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
		console.log("Current scene: opponentTurn");
	},
	update: function () {
		if (Date.now() - lastBoardUpdate > 3000) {
			checkersBoard.downloadBoard();
			lastBoardUpdate = Date.now();
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
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
		if (Date.now() - lastBoardUpdate > 3000) {
			checkersBoard.downloadBoard();
			lastBoardUpdate = Date.now();
		}
	},
	draw: function () {

	},
	cleanUp: function () {

	}
});

scene("gameOver", {
	init: function () {
		console.log("Current scene: gameOver");		
		
	},
	update: function () {
		if (Date.now() - lastBoardUpdate > 3000) {
			checkersBoard.downloadBoard();
			lastBoardUpdate = Date.now();
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
	},
	cleanUp: function () {

	}
});

scene("spectating", {
	init: function () {

	},
	update: function () {
		if (Date.now() - lastBoardUpdate > 3000) {
			checkersBoard.downloadBoard();
			lastBoardUpdate = Date.now();
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
	},
	cleanUp: function () {

	}
});


function getMouseBoardCoords() {
	var mx = mouse.x - (DOM.canvas.getBoundingClientRect().left + 6 + document.documentElement.scrollLeft);
	mx /= (DOM.canvas.width / 8);
	mx = Math.floor(mx);
	var my = mouse.y - (DOM.canvas.getBoundingClientRect().top + 6 + document.documentElement.scrollTop);
	my /= (DOM.canvas.height / 8);
	my = Math.floor(my);
	return {
		x: mx,
		y: my
	};
}

lastBoardUpdate = 0;