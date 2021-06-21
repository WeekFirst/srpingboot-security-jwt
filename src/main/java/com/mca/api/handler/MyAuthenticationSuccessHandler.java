package com.mca.api.handler;

import com.mca.api.util.JwtTokenUtil;
import com.mca.api.util.ResponseUtil;
import com.mca.api.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;

/**
 * @Author an Stark
 * @Description: 登录成功处理器
 * @Date 2021/6/19 13:25
 * @Version 1.0
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //签发token
        String name = authentication.getName();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        StringBuilder stringBuffer = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            stringBuffer.append(authority);
            stringBuffer.append(",");
        }
        StringBuilder stringBuilder = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        String token = JwtTokenUtil.createToken(name, stringBuilder.toString());
        httpServletResponse.setHeader("Authorization" , "Bearer" + " " + token);
        ResponseUtil.responseOut(httpServletResponse, Result.ok(authentication));
    }
}
