package com.imooc.houjiangtao.alllearning.domain.common;

import com.imooc.houjiangtao.alllearning.exception.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(
        value = "统一返回结果实体",
        description = "封装统一返回结果信息实体"
)
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -31677365028363832L;
    @ApiModelProperty(name = "success",
            value = "是否成功",
            required = true, dataType = "Boolean")
    private Boolean success;
    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty("信息")
    private String message;
    @ApiModelProperty("结果")
    private T result;

    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        return responseResult;
    }

    public static <T> ResponseResult<T> failure(String code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }

    public static <T> ResponseResult<T> failure(ErrorCodeEnum errorCodeEnum) {
        return failure(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }
}
