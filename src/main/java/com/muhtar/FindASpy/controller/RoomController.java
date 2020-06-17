package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Player;
import com.muhtar.FindASpy.model.Room;
import com.muhtar.FindASpy.service.RoomsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomsPool roomsPool;

    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping
    public String getAllRooms(Model model) {
//        List<String> usersList = sessionRegistry.getAllPrincipals().stream()
//                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
//                .map(Object::toString)
//                .collect(Collectors.toList());
//        model.addAttribute("activeUsers", usersList);
//        System.err.println(usersList);
//        System.err.println(sessionRegistry.getAllPrincipals());
////        System.err.println(sessionRegistry.getAllSessions(model.getAttribute("principal"), true));

        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<String> usersNamesList = new ArrayList<>();

        for (Object principal: principals) {
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getUsername());
            }
        }
        System.err.println(usersNamesList);

        model.addAttribute("rooms", roomsPool.getAllNonPrivateRooms());
        System.err.println("in rooms GetMapping");
        return "rooms";
    }

    @GetMapping("/{roomId}")
    public String enterRoom(Model model,
                            @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("room", roomsPool.getRoomByStringId(roomId));
        System.err.println("/{roomId} Controller: " + roomId);
        return "room";
    }

    @PostMapping
    public String createRoom(Model model) {

//        Session.Cookie creatorPlayerCookie = (Session.Cookie) model.getAttribute("cookie");
        int maxPlayers = (int)model.getAttribute("maxPlayers");
        boolean privacyStatus = (boolean) model.getAttribute("privacy");
        String name = (String)model.getAttribute("username");
        roomsPool.addRoom(
                Room.builder().players(
                        new ArrayList<>(Arrays.asList(
                                Player.builder()
                                        .nickname(name)
                                        .cookie(new Session.Cookie())
                                        .build()
                        ))
                ).maxPlayersAmount(maxPlayers)
                 .isPrivate(privacyStatus)
                 .build()
        );
        return "";
    }
}
