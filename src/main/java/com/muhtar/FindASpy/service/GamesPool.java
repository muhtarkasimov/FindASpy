package com.muhtar.FindASpy.service;

import com.muhtar.FindASpy.model.Game;
import com.muhtar.FindASpy.model.Room;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ToString
@Component
public class GamesPool {

    @Autowired
    SymbolGenerator symbolGenerator;

    private static Map<String, Game> map;
    int generatingStringLength = 10;

    public GamesPool() {
        map = new HashMap<>();
    }

    public String addGame(String gameId, Game game) {
        map.put(gameId, game);
        return gameId;
    }

    public String addGame(Game game) {
        String generatedString = symbolGenerator.generateString(generatingStringLength);
        while (map.containsKey(generatedString)) {
            System.err.println("Game String generating: Collision happened!");
            generatedString = symbolGenerator.generateString(generatingStringLength);
        }
        map.put(generatedString, game);
        return generatedString;
    }

    public Game getGameByStringId(String id) {
        return map.get(id);
    }

    public Map<String, Game> getAllGames() {
        return map;
    }

//    public Map<String, Game> getAllNonPrivateGames() {
//        HashMap<String, Room> nonPrivateRooms = new HashMap<>();
//        for (Map.Entry<String, Room> entry : map.entrySet()) {
////            System.out.println(entry.getKey() + "/" + entry.getValue());
//            if(!(entry.getValue().isPrivate())) {
//                nonPrivateRooms.put(entry.getKey(), entry.getValue());
//            }
//        }
//        return nonPrivateRooms;
//    }


    public void removeGame(String id) {
        map.remove(id);
    }
}