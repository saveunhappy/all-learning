package com.imooc.houjiangtao.alllearning.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1568067334195415830L;
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

    /* 系统主信息 */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //创建时间
    private LocalDateTime created;
    //修改时间
    private LocalDateTime modified;
    //创建者
    private String creator;
    //最后修改者
    private String operator;
    /**
     * 逻辑删除字段 ，0 正常，1：逻辑删除
     */
    private Integer status;
    @Version
    private Long version;

}
