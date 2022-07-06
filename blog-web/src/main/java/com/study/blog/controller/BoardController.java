package com.study.blog.controller;

import com.study.blog.config.auth.UserDetail;
import com.study.blog.dto.ResponseDto;
import com.study.blog.model.Board;
import com.study.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("boards", boardService.getBoardList());
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "/board/saveForm";
    }

    @ResponseBody
    @PostMapping("/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal UserDetail user) {
        boardService.write(board, user.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
