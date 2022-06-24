package com.study.blog.controller;

import com.study.blog.model.User;
import com.study.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("user/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @ResponseBody
    @PostMapping("user/join")
    public int join(@RequestBody User user) {
        return userService.join(user);
    }

}
