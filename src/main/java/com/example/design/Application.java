package com.example.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * spring app 的运行接口
 * Created by lxh on 4/13/16.
 */
@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
