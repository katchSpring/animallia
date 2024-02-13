package com.sparta.newsfeed_project.controller;

import com.sparta.newsfeed_project.dto.LoginRequestDto;
import com.sparta.newsfeed_project.dto.SignupRequestDto;
import com.sparta.newsfeed_project.dto.UserRequestDto;
import com.sparta.newsfeed_project.dto.UserResponseDto;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.jwt.JwtUtil;
import com.sparta.newsfeed_project.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/user/signup-page")
    public String signup() {
        return "signup";
    }

    @GetMapping("/user/login-page")
    public String login() {
        JwtUtil jwtUtil = new JwtUtil();
        return "login-page";
    }

    @PostMapping("/user/signup")
    public void signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>("login-sucess",HttpStatus.OK);
    }

  //프로필 단건 조회
  @GetMapping("/user/profile/{id}")
    public ResponseEntity<UserResponseDto> getProfile(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return ResponseEntity.ok().body(userResponseDto);
    }

    //프로필 수정
    @PutMapping("/user/profile/{id}")
    public ResponseEntity<UserResponseDto> updateProfile(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto updateProfile = userService.updateProfile(id, userRequestDto);
            return ResponseEntity.ok().body(updateProfile);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
