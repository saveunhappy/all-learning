package com.imooc.houjiangtao.alllearning.mapper;

import com.imooc.houjiangtao.alllearning.domain.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void find(){
        Map<String, Object> params = new HashMap<>();
        params.put("username","username1");
        List<UserDO> userDOS = userMapper.selectByMap(params);
        log.info("{}",userDOS);

    }
}