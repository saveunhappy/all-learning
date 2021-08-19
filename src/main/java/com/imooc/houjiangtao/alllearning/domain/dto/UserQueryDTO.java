package com.imooc.houjiangtao.alllearning.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 6391208741143957045L;
    @NotEmpty(message = "用户姓名不能为空！")
    private String username;

}
