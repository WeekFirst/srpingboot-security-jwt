package com.mca.api.controller;

import com.mca.api.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author an Stark
 * @ClassName IndexController
 * @Description TODO
 * @date 2021/6/21 下午4:04
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/index")
@Slf4j
public class IndexController {

    @GetMapping(value = "/first")
    public Result indexFirst(){
        return Result.ok("index");
    }



}
