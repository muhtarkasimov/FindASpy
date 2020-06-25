package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/rooms")
    @SendTo("/listener/roomName")
    public Message messageToRooms(Message message) throws Exception {
        System.err.println("IN MESSAGE CONTROLLER: " + message);
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        return message;
    }

}
