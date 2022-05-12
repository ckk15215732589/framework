package com.ckk.demo.monomer.common.fo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginFo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码key")
    private String key;

    @ApiModelProperty(value = "验证码code")
    private String Code;

}
