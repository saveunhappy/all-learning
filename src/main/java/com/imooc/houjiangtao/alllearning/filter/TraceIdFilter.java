package com.imooc.houjiangtao.alllearning.filter;


import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/*")
@Order(1)
public class TraceIdFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = servletRequest.getParameter(TRACE_ID);
        if(StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString();
        }
        //在MDC中放入traceId
        MDC.put(TRACE_ID,traceId);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

