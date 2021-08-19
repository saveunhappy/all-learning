package com.imooc.houjiangtao.alllearning.domain.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageQuery<T> implements Serializable {
    private static final long serialVersionUID = -1881440247206911714L;
    //当前页
    private Integer pageNo = 1;
    //每页条数
    private Integer pageSize = 20;
    //动态查询条件
    private T query;
}
