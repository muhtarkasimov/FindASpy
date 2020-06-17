package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Room {

    List<User> users;
    int maxPlayersAmount;
    boolean isPrivate;

}
