package com.imooc.houjiangtao.alllearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//webFilter起作用
@ServletComponentScan
public class AllLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllLearningApplication.class, args);
    }

}
