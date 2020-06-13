package com.muhtar.FindASpy.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Room {

    List<Player> players;
    int maxPlayersAmount;
    boolean isPrivate;

}
