package com.sparta.newsfeed_project.dto;

import com.sparta.newsfeed_project.entity.Post;
//import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.User;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PostResponseDto {

    private Long id;
    private String content;
    private String title;
    private Long user_id;
    @Builder
    public PostResponseDto(Post post){
        this.id=post.getId();
        this.content = post.getContent();
        this.title =post.getTitle();
        this.user_id= post.getUser().getUser_id();
    }
}
