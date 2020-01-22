### 项目SpringBoot-Start
##### 描述：SpringBoot项目学习第一步
##### 工具：IDEA
##### 项目：Maven

---

#### 1.开始构建项目：
&emsp;&emsp;1、访问 http://start.spring.io/

&emsp;&emsp;2、选择构建工具 Maven Project、Java、Spring Boot 版本 2.2.4 以及一些工程基本信息

&emsp;&emsp;3、点击 Generate Project 下载项目压缩包

&emsp;&emsp;4、解压后，使用 Idea 导入项目，File -> New -> Model from Existing Source.. -> 选择解压后的文件夹 -> OK，选择 Maven 一路 Next，OK done!

&emsp;&emsp;5、如果使用的是 Eclipse，Import -> Existing Maven Projects -> Next -> 选择解压后的文件夹 -> Finsh，OK done!

##### 项目结构介绍
```
SpringBootOne-Start:.          
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─wml
│  │  │          │  SpringBootOneStartApplication.java #SpringBoot 项目入口(启动类)
│  │  │          │  
│  │  │          └─controller
│  │  │                  HelloWorldController.java 
│  │  │                  #自建的Controller类，用于显示‘Hello world’
│  │  │                  
│  │  └─resources
│  │          application.properties 
│  │          #主要的配置文件，SpringBoot启动时候会自动加载application.yml/application.properties
│  │          
│  └─test
│      └─java
│          └─com
│              └─wml
│                  │  SpringBootOneStartApplicationTests.java
│                  │  
│                  └─controller
│                          HelloWorldControllerTest.java #Controller测试类
│                          
└─target
    │                  
    └─classes
```

#### 2.pom.xml 文件中的相关配置
**pom.xml 文件中默认有两个模块：**

+ `spring-boot-starter` ：核心模块，包括自动配置支持、日志和 YAML，如果引入了 `spring-boot-starter-web` web 模块可以去掉此配置，因为 spring-boot-starter-web 自动依赖了 spring-boot-starter。
+ `spring-boot-starter-test` ：测试模块，包括 JUnit、Hamcrest、Mockito。

&emsp;&emsp;这里我们需要引入web模块，如果创建项目时没有选中web模块的引入，需要手动添加。因为该项目主要介绍SpringBoot的web应用。
```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
**pom.xml 文件中配置如下：**
```
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <!--配置SpringBoot父级依赖，它用来提供相关的Maven默认依赖，使用
     它之后常用的包依赖可以省去version标签-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.4.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependencies>
        <!-- 内嵌了tomcat服务器，开发简单的web应用 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 测试包 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

    </dependencies>
    <!--配置SpringBoot Maven插件-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```
#### 3.主函数入口
**注意事项：**

&emsp;&emsp;一个项目中切记不要出现多个 main 函数，否在在打包的时候 spring-boot-maven-plugin 将找不到主函数（主动指定打包主函数入口除外…）
```
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
```
#### 4.编写一个Controller类
```
@RestController
public class HelloWorldController {
    @RequestMapping("/hello.do")
    public String sayHello(){
        return "Hello world! Hello SpringBoot...";
    }

    /**
     * @RestController 等同于 （@Controller 与 @ResponseBody）。意思就是
     * Controller 里面的方法都以 json 格式输出，不用再写什么 jackjson 配置的了！
     */
}
```
#### 5.启动主程序

&emsp;&emsp;打开浏览器访问 `http://localhost:8080/hello.do`，就可以看到效果了

#### 6.初窥配置文件

&emsp;&emsp;从启动日志中可以发现，SpringBoot 默认的端口是 8080 ，那么如果端口被占用了怎么办呢？不要慌，问题不大，配置文件分分钟解决你的困扰…
```
# 默认的 8080 我们将它改成 9090 
server.port=9090
# 未定义上下文路径之前 地址是 http://localhost:8080 定义了后 http://localhost:9090 你能在tomcat做的事情，配置文件都可以
server.servlet.context-path=/SpringBootOne-Start
```
&emsp;&emsp;打开浏览器访问 `http://localhost:9090/SpringBootOne-Start/hello.do`，就可以看到效果了
       
#### 7.如何做单元测试
```
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
```
**为何使用MockMvc？**

&emsp;&emsp;对模块进行集成测试时，希望能够通过输入URL对Controller进行测试，如果通过启动服务器，建立http client进行测试，这样会使得测试变得很麻烦，比如：启动速度慢，测试验证不方便，依赖网络环境等，所以为了可以对Controller进行测试，我们引入了MockMVC。

&emsp;&emsp;MockMvc实现了对Http请求的模拟，能够直接使用网络的形式，转换到Controller的调用，这样可以使得测试速度快、不依赖网络环境，而且提供了一套验证的工具，这样可以使得请求的验证统一而且很方便。

**测试逻辑**

1.MockMvcBuilder构造MockMvc的构造器；

2.mockMvc调用perform，执行一个RequestBuilder请求，调用controller的业务处理逻辑；

3.perform返回ResultActions，返回操作结果，通过ResultActions，提供了统一的验证方式；

4.使用StatusResultMatchers对请求结果进行验证；

5.使用ContentResultMatchers对请求返回的内容进行验证；



