let createRoom = function () {
    alert('room created');
    goToRooms();
    //tell 'created' or 'not created'
};
let goToRooms = function () {
    // alert('called: hrefToRooms');
    window.location.href = 'roomsList.html';
};