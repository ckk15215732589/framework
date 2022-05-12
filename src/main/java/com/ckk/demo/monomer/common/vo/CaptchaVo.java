package com.ckk.demo.monomer.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaVo {

    @ApiModelProperty(value = "验证码key")
    private String key;

    @ApiModelProperty(value = "验证码图片")
    private String captchaImg;

}
