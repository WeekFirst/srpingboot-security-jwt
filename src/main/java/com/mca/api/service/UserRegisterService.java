package com.mca.api.service;

import com.mca.api.entity.UserInfoPo;
import com.mca.api.util.ServiceResult;

/**
 * @Author an Stark
 * @ClassName UserRegisterService
 * @Description 用户注册接口
 * @date 2021/6/21 上午10:35
 * @Version 1.0
 */
public interface UserRegisterService {

    public ServiceResult<Boolean> userRegister(UserInfoPo user);
}
