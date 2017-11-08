<!doctype html>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" href="/css/game.css">
        <script type="text/javascript" src="/js/CheckersBoard.js"></script>
        <script type="text/javascript" src="/js/Render.js"></script>
        <script type="text/javascript" src="/js/Main.js"></script>
    </head>
    <body>
        <div id="sidebar">
            <ul>
                <li><a href="./">Sign Out</a></li>
                <li class="user"><img src="" class="userPic" style="display: none" />Efe Ozturkoglu</li>
                <li class="title">My Games</li>
                <li><a href="#">Person</a></li>
                <li class="title">Other Games</li>
                <li><a href="#">Something</a></li>
                <li><a href="#">Efe</a></li>
                <li><a href="#">Steven</a></li>
                <li><a href="#">Ozai</a></li>
            </ul>
        </div>
        <div id="content">
            <div id="boardContainer">
                <div class="topbar">
                    <p class="player left">Player 1 Name</p>
                    <p class="player right">Player 2 Name</p>
                </div>
                <canvas id="board" height="500" width="500">
                    Your browser does not support HTML5 canvas. Please update your browser.
                </canvas>
            </div>
        </div>
    </body>
</html>