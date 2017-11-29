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
 * @param {Object} lobby - the lobby JSON object from an HTTP request
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
            "\n<li><a href='#' onclick='startGameWith(\"" + playerID +
                "\")'>" + username +"</a></li>";
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
    clearSidebarLobby();
    populateSidebarGames(1, fullLobby.currentPlayerGames);
    populateSidebarGames(2, fullLobby.allGames);
}

/**
 * Updates the part of the sidebar that holds just the current player's games
 * @param {Object} currentPlayerGames - JSON object
 */
function populateCurrentGames(currentPlayerGames) {
    if (currentPlayerGames.lastUpdated > domLastUpdated.currentPlayerGames) {
        populateGamesHelper(currentPlayerGames);
    }
}

/**
 * Updates the part of the sidebar that holds just the games the current player
 * is not playing
 * @param {Object} allGames - JSON object
 */
function populateAllGames(allGames) {
    
}

function clearSidebarLobby() {
    var lobbyElmChild = document.querySelector("#mainSidebar > ul");
    var lobbyListItems = lobbyElmChild.children;
    // Keeps track of where we are in the sidebar so that we don't delete
    // elements we're not supposed to
    var sectionCounter = 0;
    for (var i = lobbyListItems.length - 1; (i > 0 && sectionCounter < 2); i--) {
        if (lobbyListItems[i].classList.contains("title")) {
            sectionCounter++;
            continue;
        }
        if (sectionCounter < 2 &&
            !lobbyListItems[i].classList.contains("title")) {
            lobbyElmChild.removeChild(lobbyListItems[i]);
        }
    }
}

function populateSidebarGames(sectionNum, gamesObject) {
    var lobbyElmChild = document.querySelector("#mainSidebar > ul");
    var lobbyListItems = lobbyElmChild.children;
    // Keeps track of where we are in the sidebar so we know where to add the
    // given list items
    var sectionCounter = 0;
    for (var i = 0; i < lobbyListItems.length; i++) {
        if (lobbyListItems[i].classList.contains("title")) {
            sectionCounter++;
            if (sectionCounter == sectionNum) {
                if (gamesObject.list.length > 0) {
                    for (var j = 0; j < gamesObject.list.length; j++) {
                        var username = gamesObject.list[j].username;
                        var id = gamesObject.list[j].id;
                        var listNode = stringToDOMNode(
                            "<li><a href=\"#\" onclick=\"loadGame(\"" + id +"\")\">"
                                + username +"</a></li>"
                        );
                        insertAfter(lobbyListItems[i + j], listNode);
                    }
                }
                else {
                    var listNode = stringToDOMNode(
                        "<li><a href=\"javascript:void\" class=\"noGames\">No Games</a></li>"
                    );
                    insertAfter(lobbyListItems[i], listNode);
                }
            }
        }
    }
}

function populateGamesHelper(gamesObject) {
    

}

function insertAfter(referenceNode, newNode) {
    referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
}
function stringToDOMNode(htmlString) {
    parser = new DOMParser();
    node = parser.parseFromString(htmlString, "text/xml");
    if (node.nodeName.toLowerCase() == "#document")
        return node.children[0];
    return node;
}