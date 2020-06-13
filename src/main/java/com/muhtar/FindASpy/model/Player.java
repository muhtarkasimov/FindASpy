package com.muhtar.FindASpy.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.servlet.server.Session;

//внимание Это будет родительский класс для авторизованных и не авторизованных пользователей
// будет иметь куки айди и никнэйм

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player {

    String nickname;
    Session.Cookie cookie;

}
