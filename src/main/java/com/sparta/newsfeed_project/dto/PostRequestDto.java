package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.Post;
//import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private User user;
    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(this.user)
                .build();
    }
}
