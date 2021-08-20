package com.imooc.houjiangtao.alllearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig {
    @Bean("exportServiceExecutor")
    public Executor exportServiceExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数:当前机器的核心数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        //最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        //队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
        //线程池中的线程名前缀
        executor.setThreadNamePrefix("export-");
        //拒绝策略：直接拒绝
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
