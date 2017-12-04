/**
 * This file handles the game states and animations
 */

resource("kingHighlight", "image", ["img/king.png"]);
resource("kingHighlightReverse", "image", ["img/king_retard.png"]);
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


sceneStartedAt = 0;
/**
 * The scene that runs when it is the current player's turn, *before* the user
 * selects a piece to move
 */
scene("currentPlayerTurn", {
	init: function () {
		console.log("Current scene: currentPlayerTurn");
		mouseAlreadyPressed = false;
		sceneStartedAt = Date.now();
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
			if (checkersBoard.getPlayerOfPiece(x0, y0) ==
				checkersBoard.getMyPlayerNumber()) {
				setScene("playerInput");
			}
			else {
				mouseAlreadyPressed = false;
			}
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);

		var output = "Your turn";
		var timeElapsed = Date.now() - sceneStartedAt;
		var step1Time = config.timing.textFadeIn
		var step2Time = step1Time + config.timing.textDuration;
		var step3Time = step2Time + config.timing.textFadeOut;
		if (timeElapsed < step1Time) {
			renderBoardText(DOM.canvas, output, (timeElapsed / step1Time));
			// console.log("boy" + (timeElapsed / step1Time));
		}
		else if (timeElapsed < step2Time) {
			renderBoardText(DOM.canvas, output);	
		}
		else if (timeElapsed < step3Time) {
			renderBoardText(DOM.canvas, output, 1 - ((timeElapsed - step2Time) / config.timing.textFadeOut));		
		}
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
		if (mouse.leftButton && !mouseAlreadyPressed) {
			var mousepos = getMouseBoardCoords();
			x1 = mousepos.x;
			y1 = mousepos.y;
			if (x0 == x1) {
				x1 = -1;
			}
			else {
				mouseAlreadyPressed = true;
				var dx = Math.abs(x1 - x0);
				var dy = Math.abs(y1 - y0);
				if (dy > 1 && dx > 1)
					moveType = 0;
				else
					moveType = 1;
				postMove(function(response) {
					if (response != "Valid Move") {
						alert(response);
					}
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
		sceneStartedAt = Date.now();		
	},
	update: function () {
		if (Date.now() - lastBoardUpdate > 3000) {
			checkersBoard.downloadBoard();
			lastBoardUpdate = Date.now();
		}
	},
	draw: function () {
		renderBoard(DOM.canvas, checkersBoard);
		
		var playerName = "Player";
		if (checkersBoard.activePlayer == 1)
			playerName = checkersBoard.player1_Name;
		else if (checkersBoard.activePlayer == 2)
			playerName = checkersBoard.player2_Name;
		var output = playerName + "'s turn"
		var timeElapsed = Date.now() - sceneStartedAt;
		var step1Time = config.timing.textFadeIn
		var step2Time = step1Time + config.timing.textDuration;
		var step3Time = step2Time + config.timing.textFadeOut;
		if (timeElapsed < step1Time) {
			renderBoardText(DOM.canvas, output, (timeElapsed / step1Time));
			console.log("boy" + (timeElapsed / step1Time));
		}
		else if (timeElapsed < step2Time) {
			renderBoardText(DOM.canvas, output);	
		}
		else if (timeElapsed < step3Time) {
			renderBoardText(DOM.canvas, output, 1 - ((timeElapsed - step2Time) / config.timing.textFadeOut));		
		}
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
	mx /= (DOM.canvas.clientWidth / 8);
	mx = Math.floor(mx);
	var my = mouse.y - (DOM.canvas.getBoundingClientRect().top + 6 + document.documentElement.scrollTop);
	my /= (DOM.canvas.clientHeight / 8);
	my = Math.floor(my);
	if (checkersBoard.getMyPlayerNumber() == 1) {
		// mx = 7 - mx;
		// my = 7 - my;
		mx /= 7;
		my /= 7;
		mx -= .5;
		my -= .5;
		mx = (-1) * mx;
		my = (-1) * my;
		mx += .5;
		my += .5;
		mx *= 7;
		my *= 7;
		mx = Math.floor(mx);
		my = Math.floor(my);
	}
	return {
		x: mx,
		y: my
	};
}

lastBoardUpdate = 0;
config = {
	timing: {
		textFadeIn: 800,
		textDuration: 1500,
		textFadeOut: 450
	}
}