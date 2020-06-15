package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.model.Player;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;                //for DB

    List<Player> players;
    List<Player> spies;
    int spiesAmount;
//    Chat chat;
    int gameTime;
    boolean isActive;

    private void selectSpies() {}

    private void startGameTime() {}

    public Game startGame() {
        return this;
    }







}
