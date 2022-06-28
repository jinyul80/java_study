package com.study.blog.controller;

import com.study.blog.dto.ResponseDto;
import com.study.blog.model.User;
import com.study.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    private final HttpSession session;

    @Autowired
    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
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

    @ResponseBody
    @PostMapping("user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
        var principal =  userService.login(user);

        var result = new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1);

        principal.ifPresent(value -> {
            session.setAttribute("principal", value);
            result.setStatus(HttpStatus.OK.value());
            result.setData(value.getId());
        });

        return result;
    }

}
