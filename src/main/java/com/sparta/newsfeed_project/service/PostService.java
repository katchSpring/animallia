package com.sparta.newsfeed_project.service;

import com.sparta.newsfeed_project.dto.PostRequestDto;
import com.sparta.newsfeed_project.dto.PostResponseDto;
import com.sparta.newsfeed_project.entity.Post;
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

    public Post findPostId(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다"));
    }



    public Post createPost(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Post newPost = requestDto.toEntity();
        newPost.setUser(userDetails.getUser());
        System.out.println("user = " + newPost.getUser());
        return postRepository.save(newPost);
    }

}
