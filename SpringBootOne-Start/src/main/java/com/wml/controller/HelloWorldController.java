package com.wml.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello.do")
    public String sayHello(){
        return "Hello world! Hello SpringBoot...";
    }

    /**
     * @RestController 等同于 （@Controller + @ResponseBody）。意思就是
     * Controller 里面的方法都以 json 格式输出，不用再写什么 jackjson 配置的了！
     */
}
