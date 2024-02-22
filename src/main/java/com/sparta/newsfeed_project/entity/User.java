package com.sparta.newsfeed_project.entity;


import com.sparta.newsfeed_project.dto.PostRequestDto;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
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
    public Long userId;
    @Column
    private String username;
    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(length = 100)
    private String intro;


    public User(String username, String password,UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Builder
    public User(Long userId, String username, String password, String intro){
        this.userId = userId;
        this.username = username;
        this.password =password;
        this.intro = intro;
    }

}
