package com.ckk.demo.monomer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.ckk.demo.monomer.common.exception.RRException;
import com.ckk.demo.monomer.common.fo.LoginFo;
import com.ckk.demo.monomer.common.fo.UserFo;
import com.ckk.demo.monomer.common.utils.JwtTokenUtil;
import com.ckk.demo.monomer.common.utils.RedisUtil;
import com.ckk.demo.monomer.dal.entity.UserEntity;
import com.ckk.demo.monomer.dal.mapper.UserMapper;
import com.ckk.demo.monomer.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    AuthenticationManager authenticationManager;

    @Override
    public String login(LoginFo fo) {
        if (!fo.getCode().equals(redisUtil.getCacheObject(fo.getKey()))) {
            throw new RRException("验证码不正确");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(fo.getUsername(), fo.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken((UserEntity) authentication.getPrincipal());
        return token;
    }

    @Override
    public void addUser(UserFo userFo) {
        UserEntity user = new UserEntity();
        user.setUsername(userFo.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userFo.getPassword()));
        user.setPhoneNum(userFo.getPhoneNum());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userMapper.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
