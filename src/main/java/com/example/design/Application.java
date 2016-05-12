package com.example.design;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
    new SpringApplicationBuilder(Application.class).web(true).run(args);
  }
}