package com.huantek.veinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务管理
@EnableAsync
public class VeinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeinServerApplication.class, args);
    }

}
