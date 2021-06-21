package com.mca.api.controller;

import com.mca.api.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:54
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/default")
public class LoginController {

    @GetMapping(value = "/info")
    public Result info(Authentication authentication) {
        return Result.ok(authentication);
    }

    @GetMapping(value = "/infoa")
    public Result infoa(Authentication authentication) {
        return Result.ok(authentication);
    }

}
