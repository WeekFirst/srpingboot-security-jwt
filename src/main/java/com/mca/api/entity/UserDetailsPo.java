package com.mca.api.entity;

import lombok.Data;


/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:40
 * @Version 1.0
 */
@Data
public class UserDetailsPo{

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 权限 用户
     */
    private String userAuthorities;


}
