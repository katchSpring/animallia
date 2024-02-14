package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserDto {
    private String userName;

    public UserDto(User user) {
        this.userName = user.getUsername();
    }
}
