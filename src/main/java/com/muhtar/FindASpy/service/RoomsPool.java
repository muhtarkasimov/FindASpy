package com.muhtar.FindASpy.service;

import com.muhtar.FindASpy.model.Room;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ToString
@Component
public class RoomsPool {

    @Autowired
    private SymbolGenerator symbolGenerator;

    private static Map<String, Room> map;
    int generatingStringLength = 10;

    public RoomsPool() {
        map = new HashMap<>();
    }

    public String addRoom(Room room) {
        String generatedString = symbolGenerator.generateString(generatingStringLength);
        while (map.containsKey(generatedString)) {
            System.err.println("String generating: Collision happened!");
            generatedString = symbolGenerator.generateString(generatingStringLength);
        }
        map.put(generatedString, room);
        return generatedString;
    }

    public Room getRoomByStringId(String id) {
        return map.get(id);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Map<String, Room> getAllRooms() {
        return map;
    }
    
    public Map<String, Room> getAllNonPrivateRooms() {
        HashMap<String, Room> nonPrivateRooms = new HashMap<>();
        for (Map.Entry<String, Room> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
            if(!(entry.getValue().isPrivate())) {
                nonPrivateRooms.put(entry.getKey(), entry.getValue());
            }
        }
        return nonPrivateRooms;
    }
    

    public void removeRoom(String id) {
        map.remove(id);
    }
}
