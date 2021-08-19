package com.imooc.houjiangtao.alllearning.service;

import com.imooc.houjiangtao.alllearning.domain.common.PageQuery;
import com.imooc.houjiangtao.alllearning.domain.common.PageResult;
import com.imooc.houjiangtao.alllearning.domain.dto.UserDTO;
import com.imooc.houjiangtao.alllearning.domain.dto.UserQueryDTO;

import java.util.List;

public interface UserService {
    int save(UserDTO userDTO);

    int update(Long id,UserDTO userDTO);

    int delete(Long id);

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);

}
