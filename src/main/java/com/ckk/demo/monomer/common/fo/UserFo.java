package com.ckk.demo.monomer.common.fo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserFo implements Serializable {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String phoneNum;

}
