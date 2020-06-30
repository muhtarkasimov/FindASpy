let prefix = "http://localhost:8080/"

window.setInterval('getOnlineUsers()', 1000)
window.setInterval('getRooms()', 1000)

const getOnlineUsers = async () => {
    const response = await fetch(prefix + 'refresh/rooms/');
    const myJson = await response.json(); //extract JSON from the http response
    console.log(myJson)
    
    let output = '<thead> Active users: </thead> <tbody> <tr>'
    for (let i = 0; i < myJson.length; i++) {
        if (i === (myJson.length - 1)) {
            output += '<td>' + myJson[i].username + ' </td>'
        } else {
            output += '<td>' + myJson[i].username + ', </td>'
        }
    }
    let end = '</tr></tbody>'

    $('#activeUsers').html(output + end);
};

const getRooms = async () => {
    const response = await fetch(prefix + 'refresh/roomsList/');
    const myJson = await response.json(); //extract JSON from the http response
    console.log(myJson)

    let output = '<thead>' +
        '<th>Leader name</th> ' +
        '<th>Room Link</th>' +
        '<th>Players</th>' +
        '<th>Places</th> </thead> <tbody>'
    Object.keys(myJson).forEach(function(key) {
        output += '<tr>' +
            '<td>' + myJson[key].users[0].username + '</td>' +
            '<td><form action="/rooms/' + key + '">' + key + ' <button type="submit">Join</button> </form></td>' +
            '<td>' + myJson[key].users.length + '</td>' +
            '<td>' + myJson[key].maxPlayersAmount + '</td>' +
            '</tr>'
    });
    let end = '</tbody>'

    $('#roomsList').html(output + end);
};