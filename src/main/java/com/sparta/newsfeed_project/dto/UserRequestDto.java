package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    String username;
    String password;
    String intro;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .intro(intro)
                .build();

    }
}
