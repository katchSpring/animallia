package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.CommonResponseDto;
import com.sparta.newsfeed_project.entity.Post;
import com.sparta.newsfeed_project.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDto extends CommonResponseDto {
    private Long id;

    private String userName;

    private String intro;

    private List<Post> postList = new ArrayList<>();


    public UserResponseDto(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public UserResponseDto(User user) {
        this.id = user.getUserId();
        this.userName = user.getUsername();
        this.intro = user.getIntro();
    }
}