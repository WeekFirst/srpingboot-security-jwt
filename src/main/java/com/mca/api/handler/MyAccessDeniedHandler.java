package com.mca.api.handler;

import com.mca.api.util.ResponseUtil;
import com.mca.api.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author an Stark
 * @Description: 权限相关
 * @Date 2021/6/19 13:49
 * @Version 1.0
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseUtil.responseOut(httpServletResponse , Result.error("权限不足"));
    }
}
