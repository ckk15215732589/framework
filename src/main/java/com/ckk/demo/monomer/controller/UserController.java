package com.ckk.demo.monomer.controller;

import com.ckk.demo.monomer.common.fo.UserFo;
import com.ckk.demo.monomer.common.result.Result;
import com.ckk.demo.monomer.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result addUser(@Valid @RequestBody UserFo userFo) {
        userService.addUser(userFo);
        return Result.succ("增加用户成功");
    }

}
