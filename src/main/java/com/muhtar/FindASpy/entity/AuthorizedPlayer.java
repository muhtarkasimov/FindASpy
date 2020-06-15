//package com.muhtar.FindASpy.entity;
//
//
//import com.muhtar.FindASpy.model.Player;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import javax.persistence.*;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Builder
//@ToString
//@FieldDefaults(level = AccessLevel.PRIVATE)
//
//@Entity
//@Table
//public class AuthorizedPlayer extends Player {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    Long id;
//    Long games;
//    Long gamesWon;
//    Long gamesWonAsSpy;
//
//    public void incrementGamesAmountByOne(){
//        games++;
//    }
//
//    public void winGame(boolean asSpy) {
//        if (asSpy) {
//            gamesWonAsSpy++;
//        }
//        gamesWon++;
//    }
//}
