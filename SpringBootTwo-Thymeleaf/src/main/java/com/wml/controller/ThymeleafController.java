package com.wml.controller;

import com.wml.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
