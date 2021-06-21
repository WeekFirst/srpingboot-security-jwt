package com.mca.api.entity;

import lombok.Data;

/**
 * @Author an Stark
 * @ClassName UserInfoPo
 * @Description TODO
 * @date 2021/6/21 上午10:38
 * @Version 1.0
 */
@Data
public class UserInfoPo {

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户名称
     */
    private String userNick;


}
