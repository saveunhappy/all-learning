package com.imooc.houjiangtao.alllearning.service.impl;

import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSave() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(0);
        userDTO.setUsername("username1");
        userDTO.setEmail("email@email.com");
        userDTO.setPassword("password1");
        userDTO.setPhone("15011112222");
        userDTO.setVersion(1L);
        int save = userService.save(userDTO);
        log.info("{}",save);
    }
}