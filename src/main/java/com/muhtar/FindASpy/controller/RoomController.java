package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.entity.User;
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
        List<String> usersList = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
//        model.addAttribute("activeUsers", usersList);

        System.err.println("usersList: " + usersList);
        System.err.println("getAllPrincipals(): " + sessionRegistry.getAllPrincipals());
//        System.err.println(sessionRegistry.getAllSessions(model.getAttribute("principal"), true));

        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<String> usersNamesList = new ArrayList<>();
        model.addAttribute("activeUsers", usersNamesList);

        model.addAttribute("roomForm", new Room());
        model.addAttribute("userForm", new User());

        for (Object principal: principals) {
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getUsername());
            }
        }
        System.err.println("Usernames: " + usersNamesList);

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
    public String createRoom(@ModelAttribute Room room, Model model, HttpServletRequest request) {

        User user = userService.getByUsername(request.getUserPrincipal().getName());
        System.err.println("User check: " + user);

        String roomLink = roomsPool.addRoom(
                Room.builder().users(
                        new ArrayList<>(Arrays.asList(
                            user
                        ))
                )
                        .maxPlayersAmount(room.getMaxPlayersAmount())
                 .isPrivate(room.isPrivate())
                 .build()
        );
        System.err.println("Room link: " + roomLink);
        System.err.println("RoomsPool: " + roomsPool.getAllRooms());
        return "redirect:/rooms/" + roomLink;
    }
}
