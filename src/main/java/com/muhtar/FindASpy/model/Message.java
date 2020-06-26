package com.muhtar.FindASpy.model;


import com.muhtar.FindASpy.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {

    String roomId;
    String username;
    String text;
}
