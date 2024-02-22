package com.sparta.newsfeed_project.controller;

import com.sparta.newsfeed_project.entity.UserRoleEnum;
import com.sparta.newsfeed_project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final String username = "홍길동";
    private static final String password = "a35asdf";
    private static final String role = "ADMIN";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Test
    void testSignupController() throws Exception {
        mockMvc.perform(post("/api/user/signup"))
                .andExpect(status().isOk());
    }


}