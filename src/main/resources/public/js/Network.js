/**
 *  Handles all of the API calls and networking
 */


ajaxRequest = function(method, path, headers, callback) {
    this.method = method;
    this.path = path;
    this.headers = headers;
    this.callback = callback;
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