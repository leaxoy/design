package com.example.design.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger config.
 *
 * @author lxh
 * @version 0.1
 */
@Configuration
@EnableSwagger2
public class Swagger {
  /**
   * user rest api.
   */
  @Bean
  public Docket userRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(userApiInfo())
            .select()
            .paths(regex("/api/user/.*"))
            .build();
  }

  private ApiInfo userApiInfo() {
    return new ApiInfoBuilder()
            .title("Spring Boot中使用Swagger2构建RESTful APIs")
            .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
            .termsOfServiceUrl("http://localhost:3000/")
            .contact("程序猿DD")
            .version("1.0")
            .license("MIT")
            .build();
  }
}
