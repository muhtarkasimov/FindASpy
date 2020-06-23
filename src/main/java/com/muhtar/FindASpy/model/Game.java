package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Player;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table
public class Game {

    private static Random random = new Random();

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;                //for DB

    List<User> players;
    List<User> spies;
    String gameId;
    int spiesAmount;
    String word;
//    Chat chat;
    int gameTime;
    boolean isActive;

    public void selectSpies() {

        for (int i = 0; i < spiesAmount; i++) {
            User playerToAdd = players.get(random.nextInt(players.size()));
            if (!spies.contains(playerToAdd)) {
                spies.add(playerToAdd);
            }
        }
    }

    private void startGameTime() {}

    public Game startGame() {
        return this;
    }







}
