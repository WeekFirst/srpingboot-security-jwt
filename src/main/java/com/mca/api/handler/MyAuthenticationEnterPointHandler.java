package com.mca.api.handler;

import com.mca.api.util.ResponseUtil;
import com.mca.api.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author an Stark
 * @ClassName MyAuthenticationEnterPointHandler
 * @Description 未登录自定义处理器
 * @date 2021/6/21 下午1:00
 * @Version 1.0
 */
public class MyAuthenticationEnterPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.responseOut(httpServletResponse , Result.unAuth());
    }
}
