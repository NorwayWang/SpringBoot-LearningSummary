package com.wml.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldControllerTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void sayHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello.do").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
/**
 * @RunWith是JUnit的一个注解, 用来告诉JUnit不要使用内置的方式进行单元
 * 测试, 而应该使用指定的类做单元测试 对于Spring单元测试总是要使用 SpringRunner.class
 *
 * @SpringBootTest 用来指定SpringBoot应用程序的入口类, 该注解默认会
 * 根据包名逐级往上找, 一直找到一个SpringBoot主程序class为止, 然后启动
 * 该类为单元测试准备Spring上下文环境.
 */
