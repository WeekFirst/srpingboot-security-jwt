package com.mca.api.controller;

import com.mca.api.entity.UserInfoPo;
import com.mca.api.service.UserRegisterService;
import com.mca.api.util.RCode;
import com.mca.api.util.Result;
import com.mca.api.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author an Stark
 * @ClassName UserRegisterController
 * @Description 用户注册使用的接口
 * @date 2021/6/21 上午10:34
 * @Version 1.0
 */

@RestController
@Slf4j
public class UserRegisterController {


    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping(value = "register")
    public Result userRegister(@ModelAttribute("user") UserInfoPo user) {
        try {
            ServiceResult<Boolean> result = userRegisterService.userRegister(user);
            if (result.isSuccess()) {
                return Result.ok(result.getValue());
            } else {
                return Result.error(RCode.DATA_EXIT);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }


    }


}
