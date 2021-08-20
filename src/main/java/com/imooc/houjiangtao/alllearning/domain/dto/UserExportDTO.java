package com.imooc.houjiangtao.alllearning.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.imooc.houjiangtao.alllearning.util.LocalDateTImeStringConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class UserExportDTO implements Serializable {

    private static final long serialVersionUID = -10685282497781706L;
    @ExcelProperty(value = "用户名")
    private String username;
    @ExcelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty(value = "版本号")
    private Long version;
    @ExcelProperty(value = "创建时间",converter = LocalDateTImeStringConverter.class)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒")
    private LocalDateTime created;
}
