package com.bitcamp.AuthTodo.security;

import com.bitcamp.AuthTodo.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {

    private static final String JWT_SECRET = "01234567890123456789012345678901";

    /**
     * 사용자 정보를 받아서 jwt 토큰을 생성
     */
    public String create(UserEntity userEntity) {
        // 토큰 유효기간 설정
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        // jwt token 생성
        return Jwts.builder()
                // header 에 들어갈 내용 및 서명을 하기위한 jwt secret 설정
                .signWith(key, SignatureAlgorithm.HS256)
                // payload 에 들어갈 내용
                .setSubject(userEntity.getId()) // sub
                .setIssuer("todo app")
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    /**
     * 사용자로부터 토큰을 받아와 해당 토큰을 가진 사용자 id 추출
     * 토큰을 디코딩 및 파싱하여 토큰의 위조 여부를 확인하는 메서드
     */
    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
