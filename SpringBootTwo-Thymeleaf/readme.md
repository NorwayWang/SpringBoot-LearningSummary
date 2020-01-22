### 项目SpringBootTwo-Thymeleaf
##### 描述：SpringBoot项目学习第二步
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
SpringBootTwo-Thymeleaf:.          
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─wml
│  │  │          │  SpringBootTwoThymeleafApplication.java
│  │  │          │  
│  │  │          ├─controller
│  │  │          │      ThymeleafController.java
│  │  │          │      
│  │  │          └─entity
│  │  │                  Author.java
│  │  │                  
│  │  └─resources
│  │      │  application.properties
│  │      │  
│  │      └─templates
│  │              index.html
│  │              
│  └─test
│      └─java
│          └─com
│              └─wml
│                      SpringBootTwoThymeleafApplicationTests.java
│                      
└─target
    |
    classes                  
```

#### 2.Thymeleaf 模板
&emsp;&emsp;Spring Boot 推荐使用 Thymeleaf 来代替 Jsp，Thymeleaf 模板到底是什么来头呢，让 Spring 大哥来推荐，下面我们来聊聊

**Thymeleaf 介绍**

&emsp;&emsp;Thymeleaf 是一款用于渲染` XML/XHTML/HTML5 `内容的模板引擎。类似` JSP，Velocity，FreeMaker `等，它也可以轻易的与 Spring MVC 等 Web 框架进行集成作为 Web 应用的模板引擎。与其它模板引擎相比，Thymeleaf 最大的特点是能够直接在浏览器中打开并正确显示模板页面，而不需要启动整个 Web 应用。

&emsp;&emsp;好了，你们说了我们已经习惯使用了什么` Velocity,FreMaker，beetle`之类的模版，那么到底好在哪里呢？

**比一比吧**

&emsp;&emsp;Thymeleaf 是与众不同的，因为它使用了自然的模板技术。这意味着 Thymeleaf 的模板语法并不会破坏文档的结构，模板依旧是有效的XML文档。模板还可以用作工作原型，Thymeleaf 会在运行期替换掉静态值。Velocity 与 FreeMarker则是连续的文本处理器。 下面的代码示例分别使用 Velocity、FreeMarker 与 Thymeleaf 打印出一条消息：
```
Velocity: <p>$message</p>
FreeMarker: <p>${message}</p>
Thymeleaf: <p th:text="${message}">Hello World!</p>
```
&emsp;&emsp;**注意，由于 Thymeleaf 使用了 XML DOM 解析器，因此它并不适合于处理大规模的 XML 文件。**

**URL**

&emsp;&emsp;URL 在 Web 应用模板中占据着十分重要的地位，需要特别注意的是 Thymeleaf 对于 URL 的处理是通过语法 @{...} 来处理的。Thymeleaf 支持绝对路径 URL：
```
<a th:href="@{http://www.thymeleaf.org}">Thymeleaf</a>
```
&emsp;&emsp;条件求值
```
<a th:href="@{/login}" th:unless=${session.user != null}>Login</a>
```
&emsp;&emsp;for循环
```
<tr th:each="prod : ${prods}">
      <td th:text="${prod.name}">Onions</td>
      <td th:text="${prod.price}">2.41</td>
      <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
</tr>
```
&emsp;&emsp;就列出这几个吧

**页面即原型**

&emsp;&emsp;在 Web 开发过程中一个绕不开的话题就是前端工程师与后端工程师的协作，在传统 Java Web 开发过程中，前端工程师和后端工程师一样，也需要安装一套完整的开发环境，然后各类 Java IDE 中修改模板、静态资源文件，启动/重启/重新加载应用服务器，刷新页面查看最终效果。

&emsp;&emsp;但实际上前端工程师的职责更多应该关注于页面本身而非后端，使用 JSP，Velocity 等传统的 Java 模板引擎很难做到这一点，因为它们必须在应用服务器中渲染完成后才能在浏览器中看到结果，而 Thymeleaf 从根本上颠覆了这一过程，通过属性进行模板渲染不会引入任何新的浏览器不能识别的标签，例如 JSP 中的 ，不会在 Tag 内部写表达式。整个页面直接作为 HTML 文件用浏览器打开，几乎就可以看到最终的效果，这大大解放了前端工程师的生产力，它们的最终交付物就是纯的` HTML/CSS/JavaScript `文件。

#### 2.Thymeleaf 使用
&emsp;&emsp;首先要在 pom.xml 中添加对`thymeleaf`模板依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

&emsp;&emsp;然后创建一个 ThymeleafController 用来映射HTTP请求与页面的跳转，下面写了两种方式，第一种比较直观和优雅，第二种相对普遍且代码较少，且迎合从struts2跳坑的朋友们…
```
/**
 * 用来映射HTTP请求与页面的跳转
 */
@RestController
public class ThymeleafController {
    /**
     * Spring4.3以后为简化@RequestMapping(method = RequestMethod.XXX)的写法，故而将其做了一层包装，
     * 也就是现在的GetMapping、PostMapping、PutMapping、DeleteMapping、PatchMapping。
     * @GetMapping是一个作为快捷方式的组合注释@RequestMapping(method = RequestMethod.GET)。
     * @PostMapping是一个作为快捷方式的组合注释@RequestMapping(method = RequestMethod.POST)。
     */

    @GetMapping("/indexOne")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "我的第一个WEB页面");
        view.addObject("desc", "欢迎进入battcn-web 系统");
        Author author = new Author();
        author.setAge(22);
        author.setEmail("1837307557@qq.com");
        author.setUserName("Tom");
        author.setRealName("唐亚峰");
        view.addObject("author", author);
        return view;
    }

    @GetMapping("/indexTwo")
    public String index1(HttpServletRequest request) {
        // TODO 与上面的写法不同，但是结果一致。
        // 设置属性
        request.setAttribute("title", "我的第一个WEB页面");
        request.setAttribute("desc", "欢迎进入battcn-web 系统");
        Author author = new Author();
        author.setAge(22);
        author.setEmail("1837307557@qq.com");
        author.setUserName("Tom");
        author.setRealName("唐亚峰");
        request.setAttribute("author", author);
        // 返回的 index 默认映射到 src/main/resources/templates/xxxx.html
        return "index";
    }
}
```

&emsp;&emsp;最后在 src/main/resources/templates 目录下创建一个名 index.html 的模板文件，可以看到 thymeleaf 是通过在标签中添加额外属性动态绑定数据的
```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <!-- 可以看到 thymeleaf 是通过在标签里添加额外属性来绑定动态数据的 -->
        <title th:text="${title}">Title</title>
        <!-- 在/resources/static/js目录下创建一个hello.js 用如下语法依赖即可-->
        <script type="text/javascript" th:src="@{/js/hello.js}"></script>
    </head>
    <body>
        <h1 th:text="${desc}">Hello World</h1>
        <h2>=====作者信息=====</h2>
        <p th:text="'用户名：'+${author?.userName}"></p>
        <p th:text="'年龄：'+${author?.age}"></p>
        <p th:text="'真实姓名：'+${author?.realName}"></p>
        <p th:text="'用户邮箱：'+${author?.email}"></p>
        <span>你好！</span>
    </body>
</html>
```
> 静态效果

```
Hello World
=====作者信息=====
你好！
```
> 动态效果

&emsp;&emsp;启动项目，在浏览器输入：http://localhost:8080/index 可以看到渲染后的效果，真正意义上的动静分离了

> 模板热部署

&emsp;&emsp;在 IntelliJ IDEA 中使用 thymeleaf 模板的时候，发现每次修改静态页面都需要重启才生效，这点是很不友好的，百度了下发现原来是默认配置搞的鬼，为了提高响应速度，默认情况下会缓存模板。如果是在开发中请将spring.thymeleaf.cache 属性设置成 false。在每次修改静态内容时按Ctrl+Shift+F9即可重新加载了…
```
# 在配置文件中添加，开发中设置成false,便于更改文件后，自动刷新
spring.thymeleaf.cache=false 
```








