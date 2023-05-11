package com.exam.onlineexamapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.exam.onlineexamapi.mapper")
public class OnlineExamApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExamApiApplication.class, args);
    }

}
