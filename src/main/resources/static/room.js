let prefix = "http://localhost:8080/"
let roomId = document.getElementById("roomId").innerText
var gamePref = document.getElementById('gamePref');
let username = document.getElementById('username').innerText;
window.setInterval('getOnlineUsersByRoomId()', 1000);


const getOnlineUsersByRoomId = async () => {
    const response = await fetch(prefix + 'refresh/' + roomId);
    const myJson = await response.json(); //extract JSON from the http response

    let output = '<thead> Players: </thead> <tbody> <tr>'
    for (let i = 0; i < myJson.length; i++) {
        if (i === (myJson.length - 1)) {
            output += '<td>' + myJson[i].username + ' </td>'
        } else {
            output += '<td>' + myJson[i].username + ', </td>'
        }
    }
    let end = '</tr></tbody>'
    document.getElementById("playersAmount").innerHTML = myJson.length
    document.getElementById("activeUsers").innerHTML = output + end

    if (username !== myJson[0].username.toString()) {
        gamePref.classList.add("disabled");
    }
};