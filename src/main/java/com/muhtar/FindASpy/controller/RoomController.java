package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Game;
import com.muhtar.FindASpy.model.Player;
import com.muhtar.FindASpy.model.Room;
import com.muhtar.FindASpy.service.RoomsPool;
import com.muhtar.FindASpy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomsPool roomsPool;

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("roomForm", new Room());

        model.addAttribute("rooms", roomsPool.getAllNonPrivateRooms());
        System.err.println("in rooms GetMapping");
        return "rooms";
    }

    @GetMapping("/{roomId}")
    public String enterRoom(Model model,
                            @PathVariable String roomId,
                            HttpServletRequest request
    ) {
        Room room = roomsPool.getRoomByStringId(roomId);
        System.err.println("ROOMS POOL CHECK: " + roomsPool.getAllRooms());

        // if room is full, redirects to rooms
        try {
            if (room.getUsers() != null) {
                if (room.getUsers().size() == room.getMaxPlayersAmount()) {
                    return "redirect:/rooms";
                }
            }
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                System.err.println("EXCEPTION!: roomcontroller.java:75-86: apperating NullPointer");
            }
            System.err.println("EXCEPTION!: roomcontroller.java:75-86: apperating EXCEPTION");
        }

        User user = userService.getByUsername(request.getUserPrincipal().getName());

        //adding user to usersList of room if there is no player yet
        if (!room.getUsers().contains(user)) {
            room.getUsers().add(user);
        }

        //**************************************
        List<String> usersList = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());

        System.err.println("USERLIST CHECK: " + usersList);
        System.err.println("USERS CHECK: " + room.getUsers());

        room.getUsers().removeIf(u -> !usersList.contains(u.toString()));
        //**************************************


        model.addAttribute("gameForm", new Game());
        model.addAttribute("roomId", roomId);
        model.addAttribute("room", room);
        System.err.println("/{roomId} Controller: " + roomId);
        return "room";
    }

    @GetMapping("/leave/{roomId}")
    public String leaveRoom(Model model,
                            @PathVariable String roomId,
                            HttpServletRequest request
    ) {
        System.err.println("in room leave controller");
        User user = userService.getByUsername(request.getUserPrincipal().getName());
        Room room = roomsPool.getRoomByStringId(roomId);
        room.getUsers().remove(user);
        if (room.getUsers().isEmpty()) {
            roomsPool.removeRoom(roomId);
        }
        return "redirect:/rooms";
    }

    @PostMapping
    public String createRoom(@ModelAttribute Room room,
                             Model model,
                             HttpServletRequest request
    ) {
        User user = userService.getByUsername(request.getUserPrincipal().getName());
        System.err.println("User check: " + user);

        String roomLink = roomsPool.addRoom(
                Room.builder().users(new ArrayList<>())
                        .maxPlayersAmount(room.getMaxPlayersAmount())
                        .isPrivate(room.isPrivate())
                        .build()
        );
        System.err.println("Room link: " + roomLink);
        System.err.println("RoomsPool: " + roomsPool.getAllRooms());
        return "redirect:/rooms/" + roomLink;
    }
}
