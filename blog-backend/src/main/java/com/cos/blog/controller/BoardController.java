package com.cos.blog.controller;


import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.BoardService;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/board")
    public ResponseDto<Integer> join(@RequestBody Board board)
        throws Exception {
        User user = userRepository.findById(1)
                                  .orElseThrow(() -> {
                                      return new UserPrincipalNotFoundException(
                                          "해당 사용자가 없습니다. id: " + 1);
                                  });
        board.setUser(user);
        boardService.write(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @GetMapping("/api/board")
    public List<Board> pageList(@PageableDefault(size=2, sort = "id", direction = Direction.DESC)
    Pageable pageable) {
        return boardService.pageList(pageable);
    }

    @GetMapping("/api/board/{id}")
    public Board detail(@PathVariable int id) {
        return boardService.detail(id);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateBoard(@PathVariable int id, @RequestBody Board board) {
        boardService.updateBoard(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
