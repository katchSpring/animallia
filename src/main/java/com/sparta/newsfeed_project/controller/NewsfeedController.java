package com.sparta.newsfeed_project.controller;

import com.sparta.newsfeed_project.CommonResponse;
import com.sparta.newsfeed_project.dto.PostRequestDto;
import com.sparta.newsfeed_project.dto.PostResponseDto;
import com.sparta.newsfeed_project.entity.Post;
import com.sparta.newsfeed_project.security.UserDetailsImpl;
import com.sparta.newsfeed_project.service.PostService;
import com.sparta.newsfeed_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsfeedController {

    private final PostService postService;

    @GetMapping("/newsfeed")
    public ResponseEntity<CommonResponse<List<PostResponseDto>>> getPostList() {
        List<PostResponseDto> response = postService.getPostList();
        return ResponseEntity.ok()
                .body(CommonResponse.<List<PostResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("List search has been completed.")
                        .data(response)
                        .build());
    }
}
