package com.mca.api.config;

import com.mca.api.filter.JwtFilter;
import com.mca.api.handler.MyAccessDeniedHandler;
import com.mca.api.handler.MyAuthenticationFailureHandler;
import com.mca.api.handler.MyAuthenticationSuccessHandler;
import com.mca.api.handler.MyLogoutSuccessHandler;
import com.mca.api.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:23
 * @Version 1.0
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

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


        //授权
        http.authorizeRequests()
                //授权开放目录
                .antMatchers("/default/**").hasAnyAuthority("ad")
                .antMatchers("/logins").permitAll()
                //所有的请求都要认证，必须登录才能访问
                .anyRequest().authenticated()
        ;
        //异常处理
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());

        //修改未jwt 认证 将token解析在交security 接管
        http.addFilter(new JwtFilter(authenticationManager()));

        //remember me
        http.rememberMe()
                //设置数据源
                .tokenRepository(persistentTokenRepository)
                //超时时间 60 s  两周时间 60*60*24*14
                //自定义remember me 参数
//                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(60*60*24*7)
                //自定义登录逻辑
                .userDetailsService(userDetailsService);


        //登出
        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessHandler(new MyLogoutSuccessHandler());

        //关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //关闭csrf防护
        http.csrf().disable();

    }




}
