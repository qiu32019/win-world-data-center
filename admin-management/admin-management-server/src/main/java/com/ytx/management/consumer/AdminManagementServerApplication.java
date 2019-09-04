package com.ytx.management.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.ytx.management.consumer.dao")
public class AdminManagementServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminManagementServerApplication.class);
    }
}
