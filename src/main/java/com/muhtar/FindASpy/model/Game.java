package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.entity.User;
import com.muhtar.FindASpy.model.Player;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
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

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;                //for DB

    List<User> players;
    List<User> spies;
    int spiesAmount;
//    Chat chat;
    int gameTime;
    boolean isActive;

    public void selectSpies() {
    }

    private void startGameTime() {}

    public Game startGame() {
        return this;
    }







}
