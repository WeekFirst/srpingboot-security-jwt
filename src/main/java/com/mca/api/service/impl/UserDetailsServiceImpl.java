package com.mca.api.service.impl;

import com.mca.api.dao.UserDetailsDAO;
import com.mca.api.entity.UserDetailsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:33
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDAO userDetailsDAO;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetailsPo user =  userDetailsDAO.loadUserByName(name);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        return user;
        return new User(user.getUserName(), user.getUserPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUserAuthorities()));
    }
}
