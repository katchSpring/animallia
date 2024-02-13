package com.sparta.newsfeed_project.controller;

import com.sparta.newsfeed_project.CommonResponse;
import com.sparta.newsfeed_project.dto.LoginRequestDto;
import com.sparta.newsfeed_project.dto.PostRequestDto;
import com.sparta.newsfeed_project.dto.PostResponseDto;
import com.sparta.newsfeed_project.entity.Post;
//import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.jwt.JwtUtil;
import com.sparta.newsfeed_project.security.UserDetailsImpl;
import com.sparta.newsfeed_project.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
// 하이

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
@Builder
// gkdl
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil;
    @PostMapping("/posts/{id}")
    public ResponseEntity<CommonResponse<PostResponseDto>> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails = " + userDetails);

        

        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료 되었습니다.")
                        .data(null)
                        .build());
    }


    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<List<PostResponseDto>>> getPostList() {
        List<Post> postList = postService.getPostList();
        List<PostResponseDto> response = postList.stream().map(PostResponseDto::new).toList();
        return ResponseEntity.ok()
                .body(CommonResponse.<List<PostResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("리스트 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<CommonResponse<PostResponseDto>> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);

        PostResponseDto response = new PostResponseDto(post);

        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())

                        .msg("조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @Transactional
    @PutMapping("/posts/{id}")
    public ResponseEntity<CommonResponse<PostResponseDto>> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        {
            Post post = postService.updatePost(id, requestDto);
            PostResponseDto response = new PostResponseDto(post);
            // 완료
            return ResponseEntity.ok()
                    .body(CommonResponse.<PostResponseDto>builder()
                            .statusCode(HttpStatus.OK.value())
                            .msg("수정이 완료 되었습니다.")
                            .data(response)
                            .build());
        }

    }


}
