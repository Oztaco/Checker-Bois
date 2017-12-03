/**
 *  Handles the rendering of the checkers board and animations
 */


renderBoard = function(canvasElm, checkerBoard, highlights) {
    ctx = canvasElm.getContext('2d');
    height = canvasElm.height;
    width = canvasElm.width;
    squareSize = width / 8;

    ctx.fillStyle = themes[currentTheme].secondaryColor;
    ctx.fillRect(0, 0, width, height);

    ctx.fillStyle = themes[currentTheme].primaryColor;
    for (var x = 0; x < 8; x++) {
        for (var y = 0; y < 8; y++) {
            if (
                !(x % 2 == 1 && y % 2 == 0) &&
                !(x % 2 == 0 && y % 2 == 1))
            {
                ctx.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }
        }
    }

    if (highlights) {
        for (var i = 0; i < highlights.length; i++) {
            ctx.fillStyle = themes[currentTheme].highlightColor;
            ctx.fillRect(highlights[i].x * squareSize, highlights[i].y * squareSize, squareSize, squareSize);
        }
    }

    var pieceSize = squareSize * .75;
    for (var x = 0; x < 8; x++) {
        for (var y = 0; y < 8; y++) {
            if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_1) {
                ctx.fillStyle = "#d00";
                ctx.strokeStyle = "#500";
                ctx.lineWidth = 3;
                ctx.beginPath();
                ctx.arc(
                    (x * squareSize) + (squareSize / 2),
                    (y * squareSize) + (squareSize / 2),
                    pieceSize / 2,
                    0,
                    2 * Math.PI
                );
                ctx.fill();
                ctx.stroke();
            }
            else if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_2) {
                ctx.fillStyle = "#ddd";
                ctx.strokeStyle = "#444";
                ctx.lineWidth = 3;
                ctx.beginPath();
                ctx.arc(
                    (x * squareSize) + (squareSize / 2),
                    (y * squareSize) + (squareSize / 2),
                    pieceSize / 2,
                    0,
                    2 * Math.PI
                );
                ctx.fill();
                ctx.stroke();
            }
        }
    }

    if (checkerBoard.playerWon != -1) {
        if (checkerBoard.player1_ID == checkerBoard.playerWon)
            var winnerName = checkerBoard.player1_Name;
        else if (checkerBoard.player2_ID == checkerBoard.playerWon)
            var winnerName = checkerBoard.player2_Name;
        else
            var winnerName = "";
        
        if (winnerName === "")
            var output = "Draw game";
        else
            var output = winnerName + " is victorious!";

        renderBoardText(canvasElm, output);
    }
}

function renderBoardText(canvasElm, text, alpha) {
    var ctx = canvasElm.getContext("2d");
    if (!alpha)
        alpha = 1.0;
    ctx.textAlign = "center";
    ctx.font = "60px Calibri";
    ctx.globalAlpha = alpha;
    ctx.fillStyle = themes[currentTheme].textShadowColor;
    ctx.fillText(text, canvasElm.width / 2 + 2, canvasElm.height / 2 + 2);
    ctx.fillStyle = themes[currentTheme].textColor;
    ctx.fillText(text, canvasElm.width / 2, canvasElm.height / 2);
    ctx.globalAlpha = 1.0;
}


currentTheme = 0;
themes = [
    {
        primaryColor: "#fdfdfd",
        secondaryColor: "#222",
        highlightColor: "#444",
        textColor: "#f22",
        textShadowColor: "#111"
    }
]
