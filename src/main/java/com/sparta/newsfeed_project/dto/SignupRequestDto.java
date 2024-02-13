package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    UserRoleEnum role;
}
