
package com.mca.api.filter;

import com.mca.api.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author an Stark
 * @Description: 解析token 并交Security 管理
 * @Date 2021/6/20 13:25
 * @Version 1.0
 */
public class JwtFilter extends BasicAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //开放index 和register 鉴权
        String uri = httpServletRequest.getRequestURI();
        if (!uri.contains("register") && !uri.contains("index") && !uri.contains("img")) {
            String authToken = httpServletRequest.getHeader("Authorization");
            final String authTokenStart = "Bearer ";
            if ((!StringUtils.isEmpty(authToken)) && authToken.startsWith(authTokenStart)) {
                authToken = authToken.substring(authTokenStart.length());
            } else {
                // 不按规范,不允许通过验证
                authToken = null;
            }
            String username = JwtTokenUtil.getUserNameFromToken(authToken);
            String authority = JwtTokenUtil.getUserRoleFromToken(authToken);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}