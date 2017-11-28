/**
 * Handles DOM manipulations and any UI code
 */

// The width of the player lobby menu
PLAYER_LOBBY_WIDTH = "200px";

// Keeps track of when the UI was updated to avoid unnecessary updates
domLastUpdated = {
    playerLobby: 0, // UNIX timestamps
    currentPlayerGames: 0,
    allGames: 0
}


/**
 * Closes or opens the UI that menu that shows a list of all the players who are
 * currently in the game
 */
function togglePlayerLobby() {
    var isVisible = DOM.playerLobby.style.width.trim().toLowerCase() == PLAYER_LOBBY_WIDTH ? true : false;
    if (isVisible) {
        hidePlayerLobby();
    }
    else {
        showPlayerLobby();
    }
}

/**
 * Hides the player lobby menu
 */
function hidePlayerLobby() {
    DOM.playerLobby.style.width = "0";
}

/**
 * Shows the player lobby menu and updates its UI by calling updatePlayerLobby()
 */
function showPlayerLobby() {
    DOM.playerLobby.style.width = PLAYER_LOBBY_WIDTH;
    updatePlayerLobby();
}

/**
 * Gets the updated player lobby configuration and updates the player lobby UI
 * accordingly
 */
function updatePlayerLobby() {
    getLobby(function (response) {
        populatePlayerLobby(JSON.parse(response).allPlayers);
    });
}

/**
 * Updates the lobby UI with UI elements representing the players who are currently
 * in the lobby.
 * @param {} lobby - the lobby JSON object from an HTTP request
 */
function populatePlayerLobby(allPlayers) {
    if (allPlayers.lastUpdated > domLastUpdated.playerLobby) {
        var lobbyElmChild = document.querySelector("#playerLobby > ul");
        var lobbyPlayers = lobbyElmChild.children;
        // Clear the current lobby
        for (var i = lobbyPlayers.length - 1; i > 0; i--) {
            lobbyElmChild.removeChild(lobbyPlayers[i]);
        }
        // Add all the users to the lobby
        for (var i = 0; i < allPlayers.list.length; i++) {
            var username = allPlayers.list[i].username;
            var playerID = allPlayers.list[i].id;
            lobbyElmChild.innerHTML +=
            "\n<li><a href='#' onclick='startGameWith(\"" + playerID + "\")'>" + username +"</a></li>";
        }

        domLastUpdated.playerLobby = allPlayers.lastUpdated;
    }
}

/**
 * Refreshes the entire sidebar UI with both the current games and all other
 * games
 * @param {Object} fullLobby - the entire JSON object recieved from the Network
 *  GET request
 */
function populateFullGamesLobby(fullLobby) {
    populateCurrentGames(fullLobby.currentPlayerGames);
    populateAllGames(fullLobby.allGames);
}

/**
 * Updates the part of the sidebar that holds just the current player's games
 * @param {Object} currentPlayerGames - JSON object
 */
function populateCurrentGames(currentPlayerGames) {

}

/**
 * Updates the part of the sidebar that holds just the games the current player
 * is not playing
 * @param {Object} allGames - JSON object
 */
function populateAllGames(allGames) {

}