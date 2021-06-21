package com.mca.api.service.impl;

import com.mca.api.dao.UserRegisterDAO;
import com.mca.api.entity.UserInfoPo;
import com.mca.api.service.UserRegisterService;
import com.mca.api.util.RCode;
import com.mca.api.util.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Author an Stark
 * @ClassName UserRegisterServiceImpl
 * @Description TODO
 * @date 2021/6/21 上午10:45
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterDAO userRegisterDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ServiceResult<Boolean> userRegister(UserInfoPo user) {
        try {
            int isExit = userRegisterDAO.checkUserAccountIsExit(user.getUserName());
            System.out.println(isExit);
            System.out.println(user.getUserName());
            if(isExit != 0){
                return ServiceResult.createFail(RCode.DATA_EXIT);
            }
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            int isSuccess =  userRegisterDAO.insertUser(user);
            if (isSuccess != 0) {
                return ServiceResult.createSuccess(true, RCode.SUCCESS);
            }else{
                return ServiceResult.createFail(RCode.DATA_EXIT);
            }


        } catch (Exception e) {
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceResult.createError(RCode.ERROR);
        }
    }



}
