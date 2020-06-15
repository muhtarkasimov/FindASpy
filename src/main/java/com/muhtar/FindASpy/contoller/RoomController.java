package com.muhtar.FindASpy.contoller;

import com.muhtar.FindASpy.model.Player;
import com.muhtar.FindASpy.model.Room;
import com.muhtar.FindASpy.service.RoomsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/")
public class RoomController {

    @Autowired
    RoomsPool roomsPool;

    @GetMapping
    public String redirectToRoomsList(Model model) {
        return "redirect:roomsList";
    }

    @GetMapping("/roomsList")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomsPool.getAllNonPrivateRooms());
        System.err.println("/roomsList Controller");
        return "rooms/roomsList";
    }

    @GetMapping("/{roomId}")
    public String enterRoom(Model model,
                            @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("room", roomsPool.getRoomByStringId(roomId));
        System.err.println("/{roomId} Controller: " + roomId);
        return "rooms/room";
    }

    @PostMapping
    public String createRoom(Model model) {
//        Session.Cookie creatorPlayerCookie = (Session.Cookie) model.getAttribute("cookie");
        roomsPool.addRoom(
                Room.builder().players(
                        new ArrayList<>(Arrays.asList(
                                Player.builder().nickname("Muha").cookie(new Session.Cookie()).build()
                        ))
                ).maxPlayersAmount(5).isPrivate(false).build()
        );
        return "";
    }
}
