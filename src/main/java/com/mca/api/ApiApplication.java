package com.mca.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * spring boot starter
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(value = {"com.mca.api"})
@MapperScan("com.mca.api.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApiApplication.class, args);
    }

}
