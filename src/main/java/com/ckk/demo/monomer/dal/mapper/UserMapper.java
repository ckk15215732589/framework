package com.ckk.demo.monomer.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ckk.demo.monomer.dal.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity selectByUsername(@Param("username") String username);
}
