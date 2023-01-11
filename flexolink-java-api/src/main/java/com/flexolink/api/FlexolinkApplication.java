package com.flexolink.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  FlexolinkApplication run
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.flexolink.api")
public class FlexolinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlexolinkApplication.class, args);
    }

}
