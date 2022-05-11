package com.ckk.demo.monomer.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("t_test")
public class TestEntity extends BaseEntity{

    private String testName;

}
