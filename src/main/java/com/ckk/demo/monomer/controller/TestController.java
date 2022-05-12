package com.ckk.demo.monomer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ckk.demo.monomer.common.fo.TestFo;
import com.ckk.demo.monomer.common.result.Result;
import com.ckk.demo.monomer.common.utils.CacheKeyUtil;
import com.ckk.demo.monomer.common.utils.PageUtil;
import com.ckk.demo.monomer.common.utils.RedisUtil;
import com.ckk.demo.monomer.common.vo.Page;
import com.ckk.demo.monomer.dal.entity.TestEntity;
import com.ckk.demo.monomer.dal.entity.UserEntity;
import com.ckk.demo.monomer.dal.mapper.TestMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @Resource
    private TestMapper testMapper;
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/insert")
    @ApiOperation("插入操作")
    public Result<String> test(@RequestParam("name") String name) {
        TestEntity entity = new TestEntity();
        entity.setTestName(name);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        testMapper.insert(entity);
        return Result.succ("success");
    }

    @GetMapping("/redis")
    @ApiOperation("redis测试")
    public Result<String> redis(@RequestParam("id") Integer id) {
        TestEntity entity = testMapper.selectById(id);
        String key = CacheKeyUtil.testKey(id);
        redisUtil.setCacheObject(key, entity);
        TestEntity value = redisUtil.getCacheObject(key);
        log.info(value.toString());
        return Result.succ("success");
    }

    @PostMapping("/page")
    @ApiOperation("分页测试")
    public Result<TestEntity> page(@RequestBody TestFo fo) {
        Page page = PageUtil.startPage(fo);
        List<TestEntity> resultList = testMapper.selectList(new QueryWrapper<>());
        page.setTotal(new PageInfo<>(resultList).getTotal());
        page.setRecords(resultList);
        log.info("分页测试");
        UserEntity entity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(entity.getUsername()+entity.getPhoneNum()+"___"+entity.getPassword());
        return Result.succ(page);
    }

}
