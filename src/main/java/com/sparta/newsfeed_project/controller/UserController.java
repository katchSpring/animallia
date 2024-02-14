package com.sparta.newsfeed_project.controller;

import com.sparta.newsfeed_project.dto.LoginRequestDto;
import com.sparta.newsfeed_project.dto.SignupRequestDto;
import com.sparta.newsfeed_project.dto.UserRequestDto;
import com.sparta.newsfeed_project.dto.UserResponseDto;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.security.UserDetailsImpl;
import com.sparta.newsfeed_project.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
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
        return "login-page";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().body("Validation failed: " + result.getAllErrors());
        }

        // Proceed with valid data
        userService.signup(requestDto);
        return ResponseEntity.ok("Data posted successfully");
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
//    @GetMapping("/user/logout")
//    public ResponseEntity<String> logout(){
//        userService.logout(requestDto,res);
//    }


  //프로필 단건 조회
  @GetMapping("/user/profile")
    public ResponseEntity<UserResponseDto> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.getUser(userDetails.getUser().getUserId());
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return ResponseEntity.ok().body(userResponseDto);
    }

    //프로필 수정
    @PutMapping("/user/profile")
    public ResponseEntity<UserResponseDto> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto updateProfile = userService.updateProfile(userDetails.getUser().getUserId(), userRequestDto);
            return ResponseEntity.ok().body(updateProfile);
        }catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
