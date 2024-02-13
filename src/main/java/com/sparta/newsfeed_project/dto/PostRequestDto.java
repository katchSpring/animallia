package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.Post;
//import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostRequestDto {
    private String title;
    private String content;
    public Post toEntity() {
        return Post.builder().title(this.title)
                .content(this.content)
                .build();
    }
}
