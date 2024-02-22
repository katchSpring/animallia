package com.sparta.newsfeed_project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.newsfeed_project.dto.PostRequestDto;
import com.sparta.newsfeed_project.dto.UserRequestDto;
import com.sparta.newsfeed_project.entity.Post;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.UserRoleEnum;
import com.sparta.newsfeed_project.repository.PostRepository;
import com.sparta.newsfeed_project.repository.UserRepository;
import com.sparta.newsfeed_project.security.MockSpringSecurityFilter;
import com.sparta.newsfeed_project.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostRequestDto postRequestDto;
    @Mock
    private MockMvc mockMvc;
    @Mock
    private User user;
    @Mock
    private UserDetailsImpl userDetailsImpl;

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() {
        User mockUser = mock(User.class);
        UserDetailsImpl mockUserDetails = mock(UserDetailsImpl.class);
        given(mockUserDetails.getUser()).willReturn(mockUser);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(mockUserDetails, null));

        mockMvc = webAppContextSetup(context).build();
    }
    @Test
    @Order(1)
    @DisplayName("게시글 작성 서비스 테스트")
    void postServiceTest() {
        // given    (값 설정)
        String title = "one";
        String content = "as123";
//        ReflectionTestUtils.setField(user, "username", "one");
        User user = new User("one","as123",UserRoleEnum.ADMIN);
        PostRequestDto postRequestDto = new PostRequestDto(title,content,user);
        // when     (가짜 객체를 넣었을때 createpost가 잘되는지?)
        postService.createPost(postRequestDto,user);
        // then     (값 확인)
    }


}