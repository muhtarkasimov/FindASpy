package com.muhtar.FindASpy.contoller;

import com.muhtar.FindASpy.service.RoomsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomsPool roomsPool;

    @GetMapping
    public String redirectToRoomsList(Model model) {
        return "redirect:rooms/roomsList";
    }

    @GetMapping("/roomsList")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomsPool.getAllNonPrivateRooms());
        System.err.println("я в контроллере");
        return "rooms/roomsList";
    }

    @GetMapping("/{roomId}")
    public String enterRoom(Model model, @PathVariable(value = "roomId", required = true) String roomId) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("room", roomsPool.getRoomByStringId(roomId));
        return "rooms/" + roomId;
    }
}
