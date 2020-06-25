//WebSocket
var stompClient = null;
connect();

function connect() {
    var socket = new SockJS('localhost:8080/gs-guide-websocket');
    console.log('Socket: ' + socket.toString());
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('StompClient: ' + stompClient)
        stompClient.subscribe('/listener/roomName', function (message) {
            displayMessage(JSON.parse(message.body));
            console.log("In subscribe message: " + message)
        });
    });
}

function sendMessage() {
    stompClient.send("/chat/rooms", {}, JSON.stringify(
        {
            'username': $("#username").text(),
            'text': $("#messageText").val()
        })
    );
}

function displayMessage(message) {
    console.log('Messaga: ' + message)
    $("#messagesBox").append("<tr><td>" + message.username + "</td> <td>" + message.text + "</td></tr>");
}

$(function () {
    $( "#sendMessage").click(function() { sendMessage(); $("#messageText").val("") });
});