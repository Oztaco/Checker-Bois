/**
 *  Handles all of the API calls and networking
 */


/**
 * Creates an ajaxRequest object which can be used to make any kind of HTTP
 * request to the server to get data.
 * @param {String} method - "GET" or "POST"
 * @param {String} path - The URL of the request
 * @param {Dictionary} headers - A dictionary where the keys are header
 *  parameters and the values are header values
 * @param {function} callback - A callback function that will utilize the
 *  response of the request
 */
ajaxRequest = function(method, path, headers, callback) {
    this.method = method;
    this.path = path;
    this.headers = headers;
    this.callback = partial(callback);
    this.self = this;
}

/**
 * Sends the created ajaxRequest, using the parameters specified by the
 * constructor
 */
ajaxRequest.prototype.send = function() {
    var xhttp = new XMLHttpRequest();
    xhttp.ajaxRequest = this; // reference to ajax request object
    xhttp.open(this.method, this.path, true);
    for (var key in this.headers) {
        if (this.headers.hasOwnProperty(key)) {           
            // console.log(key, this.headers[key]);
            xhttp.setRequestHeader(key, this.headers[key]);
        }
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            this.ajaxRequest.callback(this.responseText);
        }
    };
    xhttp.send();
}

// /**
//  * Gets a JSON object of all the boards, and sends it to the callback.
//  * TO DO: DELETE???
//  */
// getAllBoards = function(callback) {
//     var request = new ajaxRequest(
//         "GET",
//         "api/get_all_boards",
//         {},
//         function(response) {
//             callback(response);
//         }
//     )
// }
getLobby = function(callback) {
    var request = new ajaxRequest(
        "GET",
        "api/get_lobby",
        {},
        function(response) {
            callback(response);
        }
    )
    request.send();
}
getGame = function(callback, gameID) {
    var request = new ajaxRequest(
        "GET",
        "api/get_game",
        {
            "gameID": gameID
        },
        function(response) {
            callback(response);
        }
    )
    request.send();
}
postCreateBoard = function(callback, playerID) {
    var request = new ajaxRequest(
        "POST",
        "api/create_board",
        {
            "opponent_player_id": playerID
        },
        function(response) {
            callback(response);
        }
    )
    request.send();
}

postMove = function(callback, gameID, moveType, x0, y0, x1, y1) {
    var request = new ajaxRequest(
        "POST",
        "api/make_move",
        {
            "gameID": gameID,
            "moveType": moveType,
            "x0": x0,
            "y0": y0,
            "x1": x1,
            "y1": y1
        },
        function(response) {
            callback(response);
        }
    )
    request.send();
}

postResign = function(callback, gameID) {
    var request = new ajaxRequest(
        "POST",
        "api/resign",
        {
            "gameID": gameID
        },
        function(response) {
            callback(response);
        }
    )
    request.send();
}

function startGameWith(playerID) {
    hidePlayerLobby();
    postCreateBoard(function (response) {
        loadGame(response);
        getLobby(function(response) {
            var responseJSON = JSON.parse(response);
            populateFullGamesLobby(responseJSON);
        });
    }, playerID);
}

function loadGame(gameID) {
    checkersBoard.gameID = gameID;
    checkersBoard.downloadBoard();
    DOM.canvas.style.outlineColor = themes[currentTheme].secondaryColor;
}



/**
 * Helper function to allow using anonymous functions as delegates. To use, just
 * do:
 * var func = partial(f);
 * or
 * var func = partial(f, 1, 2, ...);
 * @param {*} func - the function name to callback
 */
function partial(func /*, 0..n args */) {
    var args = Array.prototype.slice.call(arguments).splice(1);
    return function () {
        var allArguments = args.concat(Array.prototype.slice.call(arguments));
        return func.apply(this, allArguments);
    };
}