package com.wml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot 项目入口
 */
@SpringBootApplication
@RestController
public class ProjectApplication {
    @RequestMapping("/")
    public String index(){
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class,args);
    }

    /**在浏览器上打开  http://localhost:8080/  查看结果*/

    /**1.@SpringBootApplication是一个组合注解，用于快捷配置启动类。
     * 之前用户使用的是3个注解注解他们的main类。分别是@Configuration,@EnableAutoConfiguration,@ComponentScan。
     * 由于这些注解一般都是一起使用，spring boot提供了一个统一的注解@SpringBootApplication。
     *
     * 2.@RestController注解等价于@Controller+@ResponseBody的结合，使用这个注解的类里面的方法都以json格式输出。
     */
}
