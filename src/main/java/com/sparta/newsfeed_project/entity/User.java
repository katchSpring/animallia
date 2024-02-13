package com.sparta.newsfeed_project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    private String username;
    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(length = 100)
    private String intro;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // mappedBy 속성을 사용하여 매핑된 엔티티의 필드명을 지정
    private List<Post> posts = new ArrayList<>(); // Post 엔티티와의 일대다 관계 설정

    public User(String username, String password,UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Builder
    public User(Long userId, String userName, String password, String intro){
        this.userId = userId;
        this.username = userName;
        this.password =password;
        this.intro = intro;
    }
}
