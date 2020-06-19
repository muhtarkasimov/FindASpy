package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.model.Game;
import com.muhtar.FindASpy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/games")
public class GameController {

//    @Autowired
//    GamePool gamePool;

    @Autowired
    UserService userService;

    @GetMapping("/{gameId}")
    public String joinGame(Model model,
                           @PathVariable String gameId,
                           HttpServletRequest request
    ) {
//        gamePool.getGameById(gameId).getUsers().add(
//                userService.getByUsername(request.getUserPrincipal().getName())
//        );
        return "";
    }

    @PostMapping
    public String createGame(@ModelAttribute Game game,
                             HttpServletRequest request
    ) {
        Game newGame = Game.builder()
                .players(game.getPlayers())
                .gameTime(game.getGameTime())
                .isActive(true)
                .spiesAmount(game.getSpiesAmount())
                .build();
        newGame.selectSpies();

        //Внимание: Вот тут рассылать редирект всем игроком на
        // автотический переход в игру
        // ...

        newGame.startGame();

        return "";
    }


}
