let prefix = "http://localhost:8080/"

window.setInterval('getOnlineUsers()', 1000)

const getOnlineUsers = async () => {
    const response = await fetch(prefix + 'online/', );
    const myJson = await response.json(); //extract JSON from the http response
    
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