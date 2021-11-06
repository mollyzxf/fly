package com.tt.fly.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author molly
 * @date 2021/11/05
 */
@SpringBootApplication(scanBasePackages = {
        "com.tt.fly.common",
        "com.tt.fly.dao",
        "com.tt.fly.service",
        "com.tt.fly.api"
})
    public class Application {

    public static void main(String[]args) {
        SpringApplication.run(Application.class);
    }
}
