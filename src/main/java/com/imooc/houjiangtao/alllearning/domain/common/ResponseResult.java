package com.imooc.houjiangtao.alllearning.domain.common;

import com.imooc.houjiangtao.alllearning.exception.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -31677365028363832L;

    private Boolean success;
    private String code;
    private String message;
    private T result;

    public static <T> ResponseResult<T> success(T result){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);
        return responseResult;
    }
    public static <T> ResponseResult<T> failure(String code,String message){
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setSuccess(false);
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }
    public static <T> ResponseResult<T> failure(ErrorCodeEnum errorCodeEnum){
       return failure(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }
}
