package com.muhtar.FindASpy.controller;

import com.google.gson.Gson;
import com.muhtar.FindASpy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/online")
public class ActiveUsersController {

    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping
    @CrossOrigin
    @ResponseBody
    public String getOnlineUsers() {
        System.err.println("GET ONLINE USERS called");
        //Вытаскиваем из РеестраСессий только активные сессии
        List<User> usersList = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(x -> (User)x)
                .collect(Collectors.toList());

        //Внимание: Сейчас на этот эндпоинт передается весь User объект
        // возможно стоит ему потом сделать модельку только с username'ом

        return new Gson().toJson(usersList);
    }
}
