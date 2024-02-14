package com.sparta.newsfeed_project.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class PostListResponseDto {
    private UserDto user;
    private List<PostResponseDto> postList;

    public PostListResponseDto(UserDto user, List<PostResponseDto> postList) {
        this.user = user;
        this.postList = postList;
    }
}
