package com.cos.blog.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    void write() {
        User user = User.builder()
                        .id(1)
                        .build();

        Board board = Board.builder()
                           .title("글쓰기 테스트1")
                           .content("Junit테스트입니다.")
                           .user(user)
                           .build();

        assertThatNoException().isThrownBy(() -> boardService.write(board));
    }

    @Test
    void detail() {
        int id = 1;

        Board result = boardService.detail(id);
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void detail_실패() {
        int id = -1;

        assertThatIllegalArgumentException()
            .isThrownBy(() -> boardService.detail(id))
            .withMessage("해당 게시글은 없습니다. id: " + id);
    }

    @Test
    @Transactional
    void deleteBoard() {
        int id = 4;

        assertThatNoException()
            .isThrownBy(() -> boardService.deleteBoard(id));

        assertThatIllegalArgumentException()
            .isThrownBy(() -> boardService.detail(id))
            .withMessage("해당 게시글은 없습니다. id: " + id);
    }

    @Test
    @Transactional
    void updateBoard() {
        int id = 4;
        Board board = Board.builder()
            .id(id)
            .title("글쓰기 테스트 4")
            .content("내용 수정했습니다")
            .build();

        assertThatNoException()
            .isThrownBy(() -> boardService.updateBoard(id, board));

        Board resBoard = boardService.detail(id);

        assertThat(resBoard.getTitle()).isEqualTo(board.getTitle());
        assertThat(resBoard.getContent()).isEqualTo(board.getContent());


    }
}