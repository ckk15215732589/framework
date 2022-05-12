package com.ckk.demo.monomer.service;

import com.ckk.demo.monomer.common.fo.LoginFo;
import com.ckk.demo.monomer.common.fo.UserFo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String login(LoginFo fo);

    void addUser(UserFo userFo);

}
