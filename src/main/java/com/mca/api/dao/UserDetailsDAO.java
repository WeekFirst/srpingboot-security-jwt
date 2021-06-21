package com.mca.api.dao;

import com.mca.api.entity.UserDetailsPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:34
 * @Version 1.0
 */
@Repository
public interface UserDetailsDAO {

    public UserDetailsPo loadUserByName(@Param("username") String username);
}
