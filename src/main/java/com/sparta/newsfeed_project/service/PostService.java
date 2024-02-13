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
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    public List<Post> getPostList() {
        return postRepository.findAll();
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

    public User findUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다"));
    }

//    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
//        return userRepository.
//    }

}
