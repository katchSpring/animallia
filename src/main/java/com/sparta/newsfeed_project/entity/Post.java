package com.sparta.newsfeed_project.entity;
import com.sparta.newsfeed_project.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@RequiredArgsConstructor
@Table(name = "post")
public class Post {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String content,User user){
        this.title =title;
        this.content =content;
        this.user = user;
    }





    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

}