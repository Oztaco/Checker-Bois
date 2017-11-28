/**
 *  Handles all of the API calls and networking
 */


ajaxRequest = function(method, path, headers, callback) {
    this.method = method;
    this.path = path;
    this.headers = headers;
    this.callback = partial(callback);
    this.self = this;
}

ajaxRequest.prototype.send = function() {
    var xhttp = new XMLHttpRequest();
    xhttp.ajaxRequest = this; // reference to ajax request object
    for (var key in this.headers) {
        if (this.headers.hasOwnProperty(key)) {           
            console.log(key, this.headers[key]);
            xhr.setRequestHeader(key, this.headers[key]);
        }
    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            this.ajaxRequest.callback(this.responseText);
        }
    };
    xhttp.open(this.method, this.path, true);
    xhttp.send();
}

getAllBoards = function(callback) {
    var request = new ajaxRequest(
        "GET",
        "api/get_all_boards",
        {},
        function(response) {
            this.callback(response);
        }
    )
}
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

function partial(func /*, 0..n args */) {
    var args = Array.prototype.slice.call(arguments).splice(1);
    return function () {
        var allArguments = args.concat(Array.prototype.slice.call(arguments));
        return func.apply(this, allArguments);
    };
}