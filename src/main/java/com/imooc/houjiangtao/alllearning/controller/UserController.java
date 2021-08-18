package com.imooc.houjiangtao.alllearning.controller;

import com.imooc.houjiangtao.alllearning.domain.common.PageResult;
import com.imooc.houjiangtao.alllearning.domain.common.ResponseResult;
import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    /**
     * POST
     */
    @PostMapping
    public ResponseResult save(@RequestBody UserDTO userDTO){
        return ResponseResult.success("新增成功");
    }
    /**
     * PUT  更新用户信息
     * api/users{id} UserDTO userDTO
     */
    @PutMapping
    public ResponseResult update(@PathVariable("id")Long id,@RequestBody UserDTO userDTO){
        return ResponseResult.success("更新成功");
    }
    @DeleteMapping
    public ResponseResult delete(@PathVariable("id")Long id){
        return ResponseResult.success("删除成功");
    }

    public ResponseResult<PageResult> query(Integer pageNum, Integer pageSize, UserQueryDTO query){
        return ResponseResult.success(new PageResult());
    }
}
