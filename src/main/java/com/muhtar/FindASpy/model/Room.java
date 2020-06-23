package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    ArrayList<User> users = new ArrayList<>();
    int maxPlayersAmount;
    boolean isPrivate;

    public boolean addUser(User user) {
        return users.add(user);
    }

}
