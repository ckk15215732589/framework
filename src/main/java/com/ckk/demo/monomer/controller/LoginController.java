package com.ckk.demo.monomer.controller;

import com.ckk.demo.monomer.common.fo.LoginFo;
import com.ckk.demo.monomer.common.result.Result;
import com.ckk.demo.monomer.common.utils.CacheKeyUtil;
import com.ckk.demo.monomer.common.utils.RedisUtil;
import com.ckk.demo.monomer.common.vo.CaptchaVo;
import com.ckk.demo.monomer.service.UserService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/index")
@Slf4j
public class LoginController {

    @Resource
    private Producer producer;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserService userService;

    @GetMapping("/captcha")
    public Result getCaptcha() throws IOException {
        //获取数字码
        String code = producer.createText();
        log.info(code);
        //生成图片
        BufferedImage image = producer.createImage(code);
        //获取输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 64 位转码
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存到redis 中 并且设置过期时间
        String key = CacheKeyUtil.captchaKey(UUID.randomUUID().toString());
        redisUtil.setCacheObject(key,code,5, TimeUnit.MINUTES);
        return Result.succ(new CaptchaVo(key, base64Img));
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginFo fo) {
        String token = userService.login(fo);
        return Result.succ(token);
    }


}
