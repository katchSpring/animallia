package com.sparta.newsfeed_project.service;

import com.sparta.newsfeed_project.dto.LoginRequestDto;
import com.sparta.newsfeed_project.dto.SignupRequestDto;
import com.sparta.newsfeed_project.dto.UserRequestDto;
import com.sparta.newsfeed_project.dto.UserResponseDto;
import com.sparta.newsfeed_project.entity.User;
import com.sparta.newsfeed_project.entity.UserRoleEnum;
import com.sparta.newsfeed_project.jwt.JwtUtil;
import com.sparta.newsfeed_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
//    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKey;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = requestDto.getRole();
        userRepository.save(new User(username, password,role));
    }

    @Transactional
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 username 입니다."));

        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
//        String refreshToken = jwtUtil.createRefreshToken(secretKey);
        jwtUtil.addJwtToHeader(token,res);
//        jwtUtil.addJwtToHeader(refreshToken,res);
//        //레디스에 저장 Refresh 토큰을 저장한다. (사용자 기본키 Id, refresh 토큰, access 토큰 저장)
//        refreshTokenRepository.save(new RefreshToken(String.valueOf(user.getUserId()), refreshToken, token));
    }



    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }


    //프로필 수정
    @Transactional
    public UserResponseDto updateProfile(Long id, UserRequestDto dto) {
        User user = checkPWAndGet(id, dto);

        user.setPassword(dto.getPassword());
        user.setIntro(dto.getIntro());

        return new UserResponseDto(user);

    }

//    //프로필 비밀번호 체크
    private User checkPWAndGet(Long id, UserRequestDto dto) {
        User user = getUser(id);

        String decodedPassword = dto.getPassword();
        // 비밀번호 체크
        if (decodedPassword != null &&
                passwordEncoder.matches(decodedPassword, passwordEncoder.encode(user.getPassword()))) {
            throw new IllegalArgumentException();
        }
        return user;
    }
}
