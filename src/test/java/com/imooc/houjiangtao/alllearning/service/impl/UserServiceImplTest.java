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

    /**
     * 乐观锁使用的规则：
     * 1.如果更新数据中不带有version字段：不使用乐观锁，并且version不会累加
     * 2.如果更新字段中带有version，但与数据库中不一致，更新失败
     * 3.如果带有version字段，并且与数据库中一致，更新成功，并且version会累加
     * @throws Exception
     */
    @Test
    public void updateTest() throws Exception{
        Long id = 1428205281275305985L;
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password11111");
        userDTO.setAge(11111);
        userDTO.setVersion(1L);
        int update = userService.update(id, userDTO);
        log.info("{}",update);

    }
    @Test
    public void testDelete() throws Exception{
        int delete = userService.delete(1428205281275305985L);
        log.info("{}",delete);
    }
}