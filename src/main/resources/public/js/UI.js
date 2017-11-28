/**
 * Handles DOM manipulations and any UI code
 */

PLAYER_LOBBY_WIDTH = "200px";

domLastUpdated = {
    playerLobby: 0, // UNIX timestamps
    currentPlayerGames: 0,
    allGames: 0
}

function togglePlayerLobby() {
    var isVisible = DOM.playerLobby.style.width.trim().toLowerCase() == PLAYER_LOBBY_WIDTH ? true : false;
    if (isVisible) {
        hidePlayerLobby();
    }
    else {
        showPlayerLobby();
    }
}
function hidePlayerLobby() {
    DOM.playerLobby.style.width = "0";
}
function showPlayerLobby() {
    DOM.playerLobby.style.width = PLAYER_LOBBY_WIDTH;
    updatePlayerLobby();
}

function updatePlayerLobby() {
    getLobby(function (response) {
        populatePlayerLobby(JSON.parse(response).allPlayers);
    });
}

function populatePlayerLobby(lobby) {
    if (lobby.lastUpdated > domLastUpdated) {
        var lobbyElmChild = document.queryElement("#playerLobby > ul");
        var lobbyPlayers = lobbyElmChild.children;
        // Clear the current lobby
        for (var i = lobbyPlayers.length - 1; i > 0; i--) {
            lobbyPlayers.removeChild(lobbyPlayers[i]);
        }
        // Add all the users to the lobby
        for (var i = 0; i < lobby.list.length; i++) {
            var username = lobby.list[i].username;
            var playerID = lobby.list[i].id;
            lobbyElmChild.innerHTML +=
                "\n<li><a href='#' onclick='startGameWith(" + username + ")'>" + playerID +"</a></li>";
        }
    }
}
{/* <div class="sidebar" id="playerLobby">
    <ul>
        <li class="title top">PLAYER LOBBY</li>
        <li><a href="#">Bill Gates</a></li>
        <li><a href="#">Stephen Rippy</a></li>
        <li><a href="#">Dennis Ritchie</a></li>
        <li><a href="#">Efe</a></li>
        <li><a href="#">Steven</a></li>
        <li><a href="#">Ozai</a></li>
    </ul>
</div> */}