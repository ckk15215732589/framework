package com.ckk.demo.monomer;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableKnife4j
@MapperScan("com.ckk.demo.monomer.dal.mapper")
public class DemoMonomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMonomerApplication.class, args);
    }

}
