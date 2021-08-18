package com.imooc.houjiangtao.alllearning.domain.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -68203752077273234L;
    /* 用户主信息 */
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //年龄
    private Integer age;
    //手机号
    private String phone;
    //版本号
    private Long version;
}
