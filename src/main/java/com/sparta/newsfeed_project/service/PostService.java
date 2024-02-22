package com.sparta.newsfeed_project.service;

import com.sparta.newsfeed_project.dto.PostRequestDto;
import com.sparta.newsfeed_project.dto.PostResponseDto;
import com.sparta.newsfeed_project.entity.Post;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.repository.PostRepository;
import com.sparta.newsfeed_project.repository.UserRepository;
import com.sparta.newsfeed_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostResponseDto> getPostList() {
        return postRepository.findAllByOrderByIdDesc().stream().map(PostResponseDto::new).toList();
    }

    public Post getPost(Long id) {
        return findPostId(id);
    }

    public Post updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPostId(id);
        post.update(requestDto);

        return postRepository.save(post);
    }
      @Transactional
    public Long deletePost(Long id) {
        postRepository.deleteById(id);
        return id;
    }

    private Post findPostId(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다"));
    }


    // entity타입으로 타입을 정하면안됨 -> 보안문제
    // 순환참조 오류
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post newPost = requestDto.toEntity();
        newPost.setUser(user);
        Post savedPost = postRepository.save(newPost);
        // dto변환
        PostResponseDto postResponseDto = new PostResponseDto(savedPost);
        // entity를 사용하고나서 반환해야한다?컨트롤러로
        return postResponseDto;
    }



}
