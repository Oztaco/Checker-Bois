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

    for (var x = 0; x < 8; x++) {
        for (var y = 0; y < 8; y++) {
            if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_1) {
                ctx.fillStyle = "#f00";
                ctx.fillRect(x * squareSize, y * squareSize, squareSize / 2, squareSize / 2);
            }
            else if (checkerBoard.getPieceAt(x, y) == BOARD_SPACE.PLAYER_2) {
                ctx.fillStyle = "#00f";
                ctx.fillRect(x * squareSize, y * squareSize, squareSize / 2, squareSize / 2);
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
