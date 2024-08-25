package com.project.board.Utils;


import com.project.board.Enum.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtToken {
    
    private final String SECRET_KEY = "XdkuHLdFXH/3qak4HMAQgAu42NQQ3548hzvhhJ55lbE=";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    
    public String encodeKey(String key){
        return Encoders.BASE64.encode(key.getBytes(StandardCharsets.UTF_8));
    }
    
    public String createToken(String userId, TokenType Token) {
        int once_second = 1000;
        int second = once_second * 60;
        int min = 60;
        int hour = 10;
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
                .signWith(key)
                .compact();
    }
    
    // 모든 클레임을 얻는 메서드
    public Claims getClaim(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
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
    
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("서명 검증 실패");
            System.out.println(e);
            return false;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("토큰 만료");
            System.out.println(e);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            // 그 외 다른 예외
            return false;
        }
    }
    
    
    
    public String getUserId(String token){
        String id = getClaim(token).getSubject();
        return id;
    }
    
    
}