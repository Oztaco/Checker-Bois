/**
 * Handles DOM manipulations and any UI code
 */

PLAYER_LOBBY_WIDTH = "200px";

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
}