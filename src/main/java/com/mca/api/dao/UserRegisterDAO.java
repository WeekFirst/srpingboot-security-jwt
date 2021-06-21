package com.mca.api.dao;

import com.mca.api.entity.UserInfoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author an Stark
 * @ClassName UserRegisterDAO
 * @Description TODO
 * @date 2021/6/21 上午10:54
 * @Version 1.0
 */
@Repository
public interface UserRegisterDAO {

    public int insertUser(UserInfoPo user);

    public int checkUserAccountIsExit(@Param("userName") String userName);

}
