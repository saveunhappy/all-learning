package com.imooc.houjiangtao.alllearning.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.houjiangtao.alllearning.exception.BusinessException;
import com.imooc.houjiangtao.alllearning.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!RATE_LIMITER.tryAcquire()) throw new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROR);
        return true;
    }
}
