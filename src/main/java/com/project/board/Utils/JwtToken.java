package com.project.board.Utils;

import com.project.board.Enum.TokenType;
import com.project.board.ExceptionHandler.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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

    public boolean checkToken(String token) {
         if(token == null || token.trim().isEmpty()){
             return false;
         }
         else if(token.chars().filter(ch -> ch == '.').count() != 2){
             return false;
         }
         else {
             return true;
         }
    }
    public void checkTokenThrow(String token){
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
        
        long time = System.currentTimeMillis();
        
        Date expriation = switch (Token) {
            case ACCESS -> new Date(1000 * 60 * 60 * 24 + time);
            case REFRESH -> new Date(1000 * 60 * 60 * 24 * 7 + time);
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
    
    public boolean checkValidateToken(String token){
        if(!checkToken(token)){
            return false;
        }
        try {
            Jwts.parserBuilder().setSigningKey(setKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException | IllegalArgumentException | ExpiredJwtException e) {
            return false;
        }
    }

    // 토큰 검증 메서드
    public boolean validateToken(String token) {
        checkTokenThrow(token);
        try {
            Jwts.parserBuilder().setSigningKey(setKey()).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            throw new IllegalArgumentException("유저 정보가 올바르지 않습니다.\n다시 로그인해 주세요.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new IllegalArgumentException("로그인 기한이 만료되었습니다.\n다시 로그인해 주세요.");
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("오류가 발생했습니다.\n다시 로그인해 주세요.");
        }
    }
    
    
    
    public String getUserId(String token){
        String id = getClaim(token).getSubject();
        return id;
    }
}