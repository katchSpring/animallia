package com.sparta.newsfeed_project;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateJwtTest {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Test
    void secretKeyIsPresent() {
        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("sercretKey 원문으로 hmac 암호화 알고리즘에 맞는 SecretKey 객체를 만들 수 있다.")
    void decodeSecretKey() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKey.getBytes());
        // Base64 인코딩된 키를 이용하여 SecretKey 객체를 만든다.
        SecretKey secretObj = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

        assertThat(secretKey).isNotNull();
        System.out.println(secretObj);
    }
}