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

    var playerNumber = checkerBoard.getMyPlayerNumber();
    ctx.setTransform(1, 0, 0, 1, 0, 0);
    if (playerNumber == 1) {
        ctx.translate(canvas.width / 2, canvas.height / 2);
        ctx.rotate(Math.PI);
        ctx.translate(-canvas.width / 2, -canvas.height / 2);
    }

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
            ctx.strokeStyle = themes[currentTheme].highlightColor;
            ctx.lineWidth = 12;
            ctx.strokeRect(highlights[i].x * squareSize + 6, highlights[i].y * squareSize + 6, squareSize - 12, squareSize - 12);
        }
    }

    var pieceSize = squareSize * .70;
    for (var x = 0; x < 8; x++) {
        for (var y = 0; y < 8; y++) {
            if (checkerBoard.getPlayerOfPiece(x, y) == 1) {
                ctx.fillStyle = themes[currentTheme].playerOneFill;
                ctx.strokeStyle = themes[currentTheme].playerOneStroke;
                ctx.lineWidth = 8;
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
                if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_1_KING) {
                    var img = res.kingHighlight.data;
                    if (playerNumber == 1)
                        var img = res.kingHighlightReverse.data;
                    ctx.drawImage(img, x * squareSize, y * squareSize, squareSize, squareSize);     
                } 
            }
            else if (checkerBoard.getPlayerOfPiece(x, y) == 2) {
                ctx.fillStyle = themes[currentTheme].playerTwoFill;
                ctx.strokeStyle = themes[currentTheme].playerTwoStroke;
                ctx.lineWidth = 8;
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
                if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_2_KING) {
                    var img = res.kingHighlight.data;
                    if (playerNumber == 1)
                        var img = res.kingHighlightReverse.data;            
                    ctx.drawImage(img, x * squareSize, y * squareSize, squareSize, squareSize);  
                }  
            }
        }
    }

    ctx.setTransform(1, 0, 0, 1, 0, 0);
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
        secondaryColor: "#BF3939",
        highlightColor: "#7C2525",
        textColor: "#f22",
        textShadowColor: "#111",
        playerOneFill: "#F7B036",
        playerOneStroke: "#C67D07",
        playerTwoFill: "#fdfdfd",
        playerTwoStroke: "#892929"
    },
    {
        primaryColor: "#fdfdfd",
        secondaryColor: "#3FB6D3",
        highlightColor: "#2D8396",
        textColor: "#f22",
        textShadowColor: "#111",
        playerOneFill: "#72E837",
        playerOneStroke: "#51A026",
        playerTwoFill: "#fdfdfd",
        playerTwoStroke: "#3394AA"
    },
    {
        primaryColor: "#fdfdfd",
        secondaryColor: "#3BCC69",
        highlightColor: "#247F41",
        textColor: "#f22",
        textShadowColor: "#111",
        playerOneFill: "#FF2B47",
        playerOneStroke: "#B21E32",
        playerTwoFill: "#fdfdfd",
        playerTwoStroke: "#2B964D"
    },
    { // old
        primaryColor: "#fdfdfd",
        secondaryColor: "#222",
        highlightColor: "#444",
        textColor: "#f22",
        textShadowColor: "#111"
    }
]
