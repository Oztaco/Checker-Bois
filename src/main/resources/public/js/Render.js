/**
 *  Handles the rendering of the checkers board and animations
 */


renderBoard = function(canvasElm, checkerBoard) {
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
                (x % 2 == 0 && y % 2 == 1) ||
                (x % 2 == 1 && y % 2 == 0))
            {
                ctx.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }
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
}


currentTheme = 0;
themes = [
    {
        primaryColor: "#fdfdfd",
        secondaryColor: "#222"
    }
]
