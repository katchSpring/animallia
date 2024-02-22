//package com.sparta.newsfeed_project.service;
//
//import com.sparta.newsfeed_project.repository.RefreshTokenRepository;
//import com.sparta.newsfeed_project.jwt.RefreshToken;
//import com.sparta.newsfeed_project.repository.RefreshTokenRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class RefreshTokenService {
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    @Transactional
//    public void saveTokenInfo(Long tokenId, String refreshToken, String accessToken) {
//        refreshTokenRepository.save(new RefreshToken(String.valueOf(tokenId), refreshToken, accessToken));
//    }
//
//    @Transactional
//    public void removeRefreshToken(String accessToken) {
//        refreshTokenRepository.findByAccessToken(accessToken)
//                .ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
//    }
//}