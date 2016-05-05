package com.example.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring app 的运行接口.
 *
 * @author lxh
 * @version 0.1
 */
@SpringBootApplication
public class Application {
  /**
   * @param args 命令行参数.
   */
  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
}