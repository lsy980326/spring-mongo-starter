package com.template.spring_mongodb.config.security;

import com.template.spring_mongodb.domain.user.User;
import com.template.spring_mongodb.exception.UserErrors;
import com.template.spring_mongodb.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * - JWT 유틸리티 클래스
 * : JWT(Json Web Token)를 생성, 검증, 추출하는 유틸리티 기능을 제공.
 *   사용자 ID와 이름을 기반으로 액세스 및 리프레시 토큰을 생성하고,
 *   토큰의 유효성을 확인하며, 특정 클레임을 추출하는 기능을 포함.
 *   유효하지 않은 토큰이거나 만료된 경우 예외를 발생시킴 (UserErrors.RequiredLogin).
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties; // JWT 관련 설정값 (시크릿 키, 만료 시간 등)

    /**
     * 주어진 JWT 토큰에서 사용자 ID를 추출합니다.
     *
     * @param token JWT 토큰
     * @return 사용자 ID
     */
    public String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("id")).toString();
    }

    /**
     * 주어진 JWT 토큰에서 사용자 이름(username)을 추출합니다.
     *
     * @param token JWT 토큰
     * @return 사용자 이름 (username)
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 주어진 JWT 토큰에서 만료 날짜를 추출합니다.
     *
     * @param token JWT 토큰
     * @return 토큰 만료 날짜
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 주어진 JWT 토큰에서 특정 클레임(Claim)을 추출합니다.
     *
     * @param token JWT 토큰
     * @param claimsResolver 클레임을 추출하는 함수
     * @return 추출된 클레임 값
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 주어진 JWT 토큰의 모든 클레임(Claims)을 추출합니다.
     *
     * @param token JWT 토큰
     * @return 토큰의 클레임 정보
     * @throws UserErrors.RequiredLogin JWT가 유효하지 않거나 잘못된 경우
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser() // JWT 파서 생성
                .verifyWith(getSecretKey()) // 시크릿 키를 사용해 검증
                .build() // 파서 빌드
                .parseSignedClaims(token) // 서명된 토큰의 클레임 추출
                .getPayload(); // 클레임 페이로드 반환
        } catch (JwtException ex) {
            log.error("JWT Error (token: {}), {}", token, ex.getMessage());
            throw new UserErrors.RequiredLogin(); // 유효하지 않은 토큰 예외 처리
        }
    }

    /**
     * 주어진 JWT 토큰이 만료되었는지 확인합니다.
     *
     * @param token JWT 토큰
     * @return true - 만료됨, false - 유효함
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 주어진 사용자 정보를 기반으로 액세스 토큰을 생성합니다.
     *
     * @param user 사용자 정보
     * @return 생성된 액세스 토큰
     */
    public String generateToken(User user) {
        return Jwts.builder()
            .claim("id", user.getId()) // 사용자 ID를 클레임에 포함
            .claim("type", TokenType.ACCESS) // 토큰 타입 설정 (액세스 토큰)
            .subject(user.getUsername()) // 사용자 이름 설정
            .issuedAt(new Date(System.currentTimeMillis())) // 발급 시간
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime())) // 만료 시간
            .signWith(getSecretKey(), Jwts.SIG.HS256) // 서명 (HMAC SHA-256)
            .compact(); // JWT 문자열 생성
    }

    /**
     * 주어진 사용자 정보를 기반으로 리프레시 토큰을 생성합니다.
     *
     * @param user 사용자 정보
     * @return 생성된 리프레시 토큰
     */
    public String generateRefreshToken(User user) {
        return Jwts.builder()
            .claim("id", user.getId()) // 사용자 ID를 클레임에 포함
            .claim("type", TokenType.REFRESH) // 토큰 타입 설정 (리프레시 토큰)
            .subject(user.getUsername()) // 사용자 이름 설정
            .issuedAt(new Date(System.currentTimeMillis())) // 발급 시간
            .expiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpirationTime())) // 만료 시간
            .signWith(getSecretKey(), Jwts.SIG.HS256) // 서명 (HMAC SHA-256)
            .compact(); // JWT 문자열 생성
    }

    /**
     * 주어진 JWT 토큰이 유효한지 검증합니다.
     *
     * @param token JWT 토큰
     * @param userDetails 인증된 사용자 정보
     * @return true - 유효함, false - 유효하지 않음
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 주어진 JWT 토큰에서 사용자 이름(username)을 추출합니다.
     *
     * @param token JWT 토큰
     * @return 사용자 이름
     */
    public String getUsernameByToken(String token) {
        return extractUsername(token);
    }

    /**
     * JWT 서명 및 검증에 사용할 시크릿 키를 생성합니다.
     *
     * @return 생성된 SecretKey
     */
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getSecret()));
    }
}