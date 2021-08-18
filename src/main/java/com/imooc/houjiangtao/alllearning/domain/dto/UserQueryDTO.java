package com.imooc.houjiangtao.alllearning.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 6391208741143957045L;
    private String username;

}
