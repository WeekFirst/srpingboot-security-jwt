# springboot-security
spring-boot + security + jwt 前后端分离，security处理结果都以json数据返回
项目结构
-src 
 -main
  -java
    -com.mca.api
     -config #项目配置类
     -controller 
     -entity
     -filter #项目过滤器
     -handler #自定义处理器
     -service
     -util #工具类
  -resources

1使用创建工具创建包含web模块和security模块的springboot项目
-如需要数据库进行操作还需引入相应数据库操作模块
2创建MyWebSecurityConfig类并继承WebSecurityConfigurerAdapter
3编写并修改WebSecurityConfigurerAdapter的相关设置见MyWebSecurityConfig
-   //授权路径相关
    http.authorizeRequests()
    //静态资源与其他个别开放目录在JwtFilter中也要设置
    .antMatchers("/register").permitAll()
    .antMatchers("/index/**").permitAll()
    .antMatchers("/img/**").permitAll()
    //所有的请求都要认证，必须登录才能访问
    .anyRequest().authenticated();
    
-   去除未登录的时候跳转security自带的登录页面 
    //未登录处理
     http.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEnterPointHandler());
