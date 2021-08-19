package com.imooc.houjiangtao.alllearning.exception;

import com.imooc.houjiangtao.alllearning.domain.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult runtimeExceptionHandle(BusinessException e){
        log.error("捕捉到业务时异常",e);
        return ResponseResult.failure(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult runtimeExceptionHandle(RuntimeException e){
        log.error("捕捉到运行时异常",e);
        return ResponseResult.failure(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),e.getMessage());
    }
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult throwableHandle(Throwable th){
        log.error("捕捉throwable异常",th);
        return ResponseResult.failure(ErrorCodeEnum.SYSTEM_ERROR.getCode(),th.getMessage());
    }
}
