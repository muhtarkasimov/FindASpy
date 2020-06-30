package com.muhtar.FindASpy.controller;

import com.google.gson.Gson;
import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Room;
import com.muhtar.FindASpy.service.RoomsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/refresh")
public class RefreshController {

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    RoomsPool roomsPool;

    @GetMapping("/rooms")
    @ResponseBody
    public String getOnlineUsersAndRooms() {
        //Вытаскиваем из РеестраСессий только активные сессии
        List<User> usersList = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(x -> (User)x)
                .collect(Collectors.toList());

        //Внимание: Сейчас на этот эндпоинт передается весь User объект
        // возможно стоит ему потом сделать модельку только с username'ом

        return new Gson().toJson(usersList);
    }

    @GetMapping("/{roomId}")
    @ResponseBody
    public String getOnlineUsersByRoomId(@PathVariable String roomId) {
        Room currentRoom = roomsPool.getRoomByStringId(roomId);
        return new Gson().toJson(currentRoom.getUsers());
    }

    @GetMapping("/roomsList")
    @ResponseBody
    public String getRooms() {
        return new Gson().toJson(roomsPool.getAllNonPrivateRooms());
    }
}
