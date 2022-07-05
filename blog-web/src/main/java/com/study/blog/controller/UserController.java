package com.study.blog.controller;

import com.study.blog.dto.ResponseDto;
import com.study.blog.model.User;
import com.study.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @ResponseBody
    @PostMapping("/auth/joinProc")
    public int join(@RequestBody User user) {
        return userService.join(user);
    }

    @GetMapping("/user/updateForm")
    public String userForm() {
        return "user/updateForm";
    }

}
