package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Game;
import com.muhtar.FindASpy.service.GamesPool;
import com.muhtar.FindASpy.service.RoomsPool;
import com.muhtar.FindASpy.service.UserService;
import com.muhtar.FindASpy.service.WordsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    GamesPool gamesPool;

    @Autowired
    RoomsPool roomsPool;

    @Autowired
    WordsPool words;

    @Autowired
    UserService userService;

    @GetMapping("/{gameId}")
    public String joinGame(Model model,
                           @PathVariable String gameId,
                           HttpServletRequest request
    ) {
        model.addAttribute("game", (Game)model.getAttribute("game"));
        model.addAttribute("gameId", gameId);

        return "gameRoom";
    }

    @PostMapping
    public String createGame(@ModelAttribute Game game,
                             @ModelAttribute(name = "roomId") String roomId,
                             Model model
    ) {
        ArrayList<User> players = roomsPool.getRoomByStringId(roomId).getUsers();
        System.err.println("RoomId : " + roomId);
        System.err.println("Players : " + players);
        Game newGame = Game.builder()
                .players(players)
                .gameTime(game.getGameTime())
                .isActive(true)
                .spiesAmount(game.getSpiesAmount())
                .word(words.getRandomWord())
                .build();
        newGame.selectSpies();

        String gameId = gamesPool.addGame(roomId ,newGame);

        //Внимание: Вот тут рассылать редирект всем игроком на
        // автотический переход в игру
        // ...

//        newGame.startGame();
        model.addAttribute("game", newGame);
        model.addAttribute("gameId", gameId);

        return "redirect:/games/" + gameId;
    }


}
