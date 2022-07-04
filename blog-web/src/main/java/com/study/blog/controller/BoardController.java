package com.study.blog.controller;

import com.study.blog.config.auth.UserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    // 컨트롤러에서 로그인유저 객체를 조회할 때
    // @GetMapping({"", "/"})
    // public String index(Model model,
    //                     @AuthenticationPrincipal UserDetail user) {
    //     model.addAttribute("principal", user);
    //     return "index";
    // }
}
