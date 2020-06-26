package com.muhtar.FindASpy.controller;

import com.muhtar.FindASpy.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/hall")
    @SendTo("/listener/hall")
    public Message messageToRooms(Message message) throws Exception {
        System.err.println("IN HALL CONTROLLER: " + message);
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        return message;
    }


    /**
     * На данный момент любое сообщение из любого Room'а отправляется на общий эндпоинт
     * /listener/rooms, оттуда каждый Room выбирает свое сообщение по RoomId
     * @param message {roomId, username, text}
     * @return возвращает message с заданным roomId
     * @throws Exception
     */
    @MessageMapping("/rooms")
    @SendTo("/listener/rooms")
    public Message messageToRoom(Message message) throws Exception {
        System.err.println("IN ROOMS CONTROLLER MESSAGE-> (" + message + ")");
        return message;
    }

}
