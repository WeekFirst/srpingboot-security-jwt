package com.mca.api.config;

import com.mca.api.filter.JwtFilter;
import com.mca.api.handler.*;
import com.mca.api.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


/**
 * @Author an Stark
 * @Description: security 核心配置类
 * @Date 2021/6/19 12:23
 * @Version 1.0
 */
@Configuration
//开启权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置存储token的数据库链接设置 在MyConfig中查看
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    //自定义UserDetailService 实现 UserDetailService
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                //登录成功自定义处理器 成功后签发token
                .successHandler(new MyAuthenticationSuccessHandler())
                //登录失败自定义处理器
                .failureHandler(new MyAuthenticationFailureHandler())
        ;
        //授权路径相关
        http.authorizeRequests()
                //静态资源与其他个别开放目录在JwtFilter中也要设置
                .antMatchers("/register").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/img/**").permitAll()
                //所有的请求都要认证，必须登录才能访问
                .anyRequest().authenticated()
        ;
        //异常处理
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        //未登录处理
        http.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEnterPointHandler());
        //remember me
        http.rememberMe()
                //设置数据源
                .tokenRepository(persistentTokenRepository)
                //超时时间 60 s  两周时间 60*60*24*14
                //自定义remember me 参数
//                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                //自定义登录逻辑
                .userDetailsService(userDetailsService);
        //登出
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessHandler(new MyLogoutSuccessHandler());
        //关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //修改未jwt 认证 将token解析在交security 接管
        http.addFilter(new JwtFilter(authenticationManager()));
        //关闭csrf防护
        http.csrf().disable();
    }
}