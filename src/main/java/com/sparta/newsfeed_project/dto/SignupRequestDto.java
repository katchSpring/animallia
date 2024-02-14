package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @Size(min = 2, max = 5,message = "최소 두자리, 최대 5자리를 입력해주세요")
    @NotBlank
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&()]{5,20}$",message = "영소문자특수문자포함 최소 5자,최대 20자로 입력해주세요")
    @NotBlank
    private String password;

    UserRoleEnum role;
}
