var stompClient = null;
var localRoomId = $('#roomId').text();
connect();

function connect() {
    var socket = new SockJS('localhost:8080/websocket');
    console.log('Socket: ' + socket.toString());
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        console.log('StompClient: ' + stompClient);
        stompClient.subscribe('/listener/rooms', function (message) {
            displayMessage(JSON.parse(message.body));
            console.log("In subscribe message: " + message)
        });
    });
}

function sendMessage() {
    stompClient.send("/chat/rooms", {}, JSON.stringify(
        {
            'roomId': localRoomId,
            'username': $("#username").text(),
            'text': $("#messageText").val()
        })
    );
}

function displayMessage(message) {
    if(message.roomId === localRoomId) {
        var today = new Date();
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        $("#messagesBox").append("<tr><td><b>" + message.username + " </b><span style='font-size: 12px; font-style: italic'>" + time + "</span> :</td> <td>" + message.text + "</td></tr>");
        scrollDiv()
    }
}

function scrollDiv() {
    var elem = $("#messageBoxScroll")
    /*******
     * This is костыль, потому, что elem.scrollHeight не отрабатывал.
     * Правильный вариант должен был выглядеть так:
     * elem.scrollTop(elem.scrollHeight)
     *******/
    elem.scrollTop(9999999999999)
}

$(function () {
    $( "#sendMessage").click(function() { sendMessage(); $("#messageText").val("") });
});