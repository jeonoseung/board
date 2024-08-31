package com.project.board.Utils;

import com.project.board.Enum.TokenType;
import com.project.board.ExceptionHandler.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtToken {
    
    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

    public String encodeKey(String key){
        return Encoders.BASE64.encode(key.getBytes(StandardCharsets.UTF_8));
    }
    
    public void checkToken(String token) {
         if(token == null || token.trim().isEmpty()){
             throw new UnauthorizedException("로그인 후 이용 가능합니다.");
         }
         else if(token.chars().filter(ch -> ch == '.').count() != 2){
             throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
         }
    }

    public SecretKey setKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String createToken(String userId, TokenType Token) {
        int once_second = 1000;
        int second = once_second * 60;
        int min = 60;
        int hour = 24;
        int day = 7;
        
        long time = System.currentTimeMillis();
        
        Date expriation = switch (Token) {
            case ACCESS -> new Date(second * min * hour + time);
            case REFRESH -> new Date(second * min * hour * day + time);
        };

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expriation)
                .signWith(setKey())
                .compact();
    }
    
    // 모든 클레임을 얻는 메서드
    public Claims getClaim(String token) {
        checkToken(token);
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(setKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
    
    public Boolean isTokenExpired(String token) {
        final Date expiration = getClaim(token).getExpiration();
        return expiration.before(new Date());
    }
    
    // 토큰 검증 메서드
    public boolean validateToken(String token) {
        checkToken(token);
        try {
            Jwts.parserBuilder().setSigningKey(setKey()).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            throw new IllegalArgumentException("서명 검증이 실패했습니다.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new IllegalArgumentException("토큰이 만료되었습니다.");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("오류가 발생했습니다.");
        }
    }
    
    
    
    public String getUserId(String token){
        String id = getClaim(token).getSubject();
        return id;
    }
}