package com.mca.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * @Author an Stark
 * @Description: token 处理
 * @Date 2021/6/18 15:51
 * @Version 1.0
 */
@Slf4j
public class JwtTokenUtil {

    private static long tokenExpiration = 24 * 60 * 60 * 1000;
    private static String tokenSignKey = "123456";
    private static String userRoleKey = "userRole";

    public static String createToken(String userName) {
        String token = Jwts.builder().setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compact();
        return token;
    }

    public static String createToken(String userName, String role) {
        String token = Jwts.builder().setSubject(userName)
                .claim(userRoleKey, role)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compact();
        return token;
    }

    public static String getUserNameFromToken(String token) {
        String userName = null;
        try {
            userName = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return userName;
    }

    public static String getUserRoleFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
            return claims.get(userRoleKey).toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
