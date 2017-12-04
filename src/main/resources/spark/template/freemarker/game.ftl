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
                username: "${username}",
                id: "${id}"
            }
        </script>
    </head>
    <body>
        <div class="sidebar" id="mainSidebar">
            <ul>
                <li class="user"><img src="" class="userPic" style="display: none" />${username}</li>
                <li><a href="./sign_out" style="color: #cc1b1b">Sign Out</a></li>
                <li><a href="#" onclick="togglePlayerLobby()">Start Game</a></li>
                <li class="title">My Games</li>
                <li><a href="#">Loading...</a></li>
                <li class="title">Other Games</li>
                <li><a href="#">Loading...</a></li>
            </ul>
        </div>
        <div class="sidebar" id="playerLobby">
            <ul>
                <li class="title top">PLAYER LOBBY</li>
                <li><a href="#">Loading</a></li>
            </ul>
        </div>
        <div id="content">
            <div id="boardContainer">
                <div class="topbar">
                    <p id="playerOneName" class="player left"></p>
                    <p id="playerTwoName" class="player right"></p>
                </div>
                <canvas id="board" height="1200" width="1200">
                    Your browser does not support HTML5 canvas. Please update your browser.
                </canvas>
                <a href="#" onclick="resign()">Resign</a>
            </div>
        </div>
    </body>
</html>