<!doctype html>
<html>
    <head>
        <title>WebCheckers</title>
        <link rel="stylesheet" href="/css/game.css">
        <script type="text/javascript" src="/js/efe.js"></script>
        <script type="text/javascript" src="/js/CheckersBoard.js"></script>
        <script type="text/javascript" src="/js/Render.js"></script>
        <script type="text/javascript" src="/js/Main.js"></script>
        <script type="text/javascript" src="/js/Game.js"></script>
        <script type="text/javascript" src="/js/Network.js"></script>
        <script type="text/javascript" src="/js/UI.js"></script>
        <script type="text/javascript">
            gameState = {
                username: "${username}"
            }
        </script>
    </head>
    <body>
        <div class="sidebar" id="mainSidebar">
            <ul>
                <li class="user"><img src="" class="userPic" style="display: none" />${username}</li>
                <li><a href="./" style="color: #cc1b1b">Sign Out</a></li>
                <li><a href="#" onclick="togglePlayerLobby()">Start Game</a></li>
                <li class="title">My Games</li>
                <li><a href="#">Person</a></li>
                <li class="title">Other Games</li>
                <li><a href="#">Something</a></li>
                <li><a href="#">Efe</a></li>
                <li><a href="#">Steven</a></li>
                <li><a href="#">Ozai</a></li>
            </ul>
        </div>
        <div class="sidebar" id="playerLobby">
            <ul>
                <li class="title top">PLAYER LOBBY</li>
                <li><a href="#">Bill Gates</a></li>
                <li><a href="#">Stephen Rippy</a></li>
                <li><a href="#">Dennis Ritchie</a></li>
                <li><a href="#">Efe</a></li>
                <li><a href="#">Steven</a></li>
                <li><a href="#">Ozai</a></li>
            </ul>
        </div>
        <div id="content">
            <div id="boardContainer">
                <div class="topbar">
                    <p class="player left">Efe Ozturkoglu</p>
                    <p class="player right">Person</p>
                </div>
                <canvas id="board" height="600" width="600">
                    Your browser does not support HTML5 canvas. Please update your browser.
                </canvas>
                <a href="#">Resign</a>
            </div>
        </div>
    </body>
</html>