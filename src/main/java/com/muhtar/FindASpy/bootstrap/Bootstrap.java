package com.muhtar.FindASpy.bootstrap;

import com.muhtar.FindASpy.entity.Role;
import com.muhtar.FindASpy.model.Player;
import com.muhtar.FindASpy.model.Room;
import com.muhtar.FindASpy.repo.RoleRepository;
import com.muhtar.FindASpy.service.RoomsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private RoomsPool roomsPool;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if (!roleRepository.findById(1L).isPresent()) {
            roleRepository.save(Role.builder().name("ROLE_USER").build());
        }
        if (!roleRepository.findById(2L).isPresent()) {
            roleRepository.save(Role.builder().name("ROLE_GUEST").build());
        }
        if (!roleRepository.findById(3L).isPresent()) {
            roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
        }
//        roomsPool.addRoom(
//                Room.builder().users(
//                        new ArrayList<>(Arrays.asList(
//                                Player.builder().nickname("Muha").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Kairat").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Azatbek").cookie(new Session.Cookie()).build()
//                                ))
//                ).maxPlayersAmount(5).isPrivate(false).build()
//        );
//        roomsPool.addRoom(
//                Room.builder().players(
//                        new ArrayList<>(Arrays.asList(
//                                Player.builder().nickname("Askhat").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Atai").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Kulanbek").cookie(new Session.Cookie()).build()
//                                ))
//                ).maxPlayersAmount(4).isPrivate(false).build()
//        );
//        roomsPool.addRoom(
//                Room.builder().players(
//                        new ArrayList<>(Arrays.asList(
//                                Player.builder().nickname("Tanir").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Xcho").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("Jah").cookie(new Session.Cookie()).build(),
//                                Player.builder().nickname("TumaniYo").cookie(new Session.Cookie()).build()
//                        ))
//                ).maxPlayersAmount(6).isPrivate(true).build()
//        );

        System.out.println(roomsPool.getAllRooms());
        System.out.println(roomsPool.getAllNonPrivateRooms());
    }
}
