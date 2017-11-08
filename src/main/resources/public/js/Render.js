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
}


currentTheme = 0;
themes = [
    {
        primaryColor: "#fdfdfd",
        secondaryColor: "#222"
    }
]
