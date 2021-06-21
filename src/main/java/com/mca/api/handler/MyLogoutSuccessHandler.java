package com.mca.api.handler;

import com.mca.api.util.ResponseUtil;
import com.mca.api.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 14:50
 * @Version 1.0
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseUtil.responseOut(httpServletResponse, Result.ok("退出成功"));
    }
}
