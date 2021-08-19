package com.imooc.houjiangtao.alllearning.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 这个就是返回给前端看的，里面的pageSize和PageNo都是MybatisPlus的那个IPage对象要映射出来的。
 *
 * @param <T>
 */
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -6612958635973206284L;

    /**
     * 当前页号
     */
    private Integer pageNo;
    /**
     * 每页行数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long pageNum;

    private T data;
}
