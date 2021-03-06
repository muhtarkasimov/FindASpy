//WebSocket
var stompClient = null;
connect();

function connect() {
    var socket = new SockJS('localhost:8080/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('StompClient: ' + stompClient)
        stompClient.subscribe('/listener/hall', function (message) {
            displayMessage(JSON.parse(message.body));
        });
    });
}

function sendMessage() {
    stompClient.send("/chat/hall", {}, JSON.stringify(
        {
            'username': $("#username").text(),
            'text': $("#messageText").val()
        })
    );
}

function displayMessage(message) {
    var today = new Date();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    $("#messagesBox").append("<tr><td><b>" + message.username + " </b><span style='font-size: 12px; font-style: italic'>"  + time +  "</span> :</td> <td>" + message.text + "</td></tr>");
    scrollDiv()
}

function scrollDiv() {
    var elem = $("#messageBoxScroll")
    /*******
     * This is костыль, потому, что elem.scrollHeight не отрабатывал.
     *******/
    elem.scrollTop(9999999999999)
}

$(function () {
    $( "#sendMessage").click(function() { sendMessage(); $("#messageText").val("") });
});