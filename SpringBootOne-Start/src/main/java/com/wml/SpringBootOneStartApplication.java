package com.wml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 项目入口(启动类)
 */
@SpringBootApplication
public class SpringBootOneStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOneStartApplication.class, args);
	}

/**@SpringBootApplication是一个组合注解，用于快捷配置启动类。
 * 之前用户使用的是3个注解注解他们的main类。分别是@Configuration,@EnableAutoConfiguration,@ComponentScan。
 * 由于这些注解一般都是一起使用，spring boot提供了一个统一的注解@SpringBootApplication。
 */
}
