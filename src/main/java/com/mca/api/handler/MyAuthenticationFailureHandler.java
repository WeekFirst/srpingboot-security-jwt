package com.mca.api.handler;

import com.mca.api.util.ResponseUtil;
import com.mca.api.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author an Stark
 * @Description: 自定义失败处理器
 * @Date 2021/6/19 13:29
 * @Version 1.0
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.responseOut(httpServletResponse , Result.error("用户名密码错误"));
    }
}
