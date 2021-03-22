package cn.jinronga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName Pagejumps
 * @Author 郭金荣
 * @Date 2021/3/14 18:18
 * @Description Pagejumps
 * @Version 1.0
 */
@Controller
public class PageJumpsController {

    @RequestMapping("/")
    public String toIndex(HttpSession session) {
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "user";
    }
}
