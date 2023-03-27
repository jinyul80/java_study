package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void write(Board board) {
        boardRepository.save(board);
    }

    public List<Board> pageList(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);
        return boardPage.getContent();
    }

    public Board detail(int id) {
        return boardRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 없습니다. id: " + id));
    }

    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Board reqBoard) {
        Board board = boardRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 없습니다. id: " + id));

        board.setTitle(reqBoard.getTitle());
        board.setContent(reqBoard.getContent());
    }

    @Transactional
    public Reply replySave(ReplySaveRequestDto replyDto) {
        User user = userRepository
            .findById(replyDto.getUserId())
            .orElseThrow(() -> {
                return new IllegalArgumentException(
                    "댓글 쓰기 실패: 해당 사용자가 없습니다. id: " + replyDto.getUserId());
            });
        Board board = boardRepository
            .findById(replyDto.getBoardId())
            .orElseThrow(() -> {
                return new IllegalArgumentException(
                    "댓글 쓰기 실패: 게시글 id를 찾을 수 없습니다. id: " + replyDto.getBoardId());
            });

        Reply reply = Reply
            .builder()
            .board(board)
            .user(user)
            .content(replyDto.getContent())
            .build();

        return replyRepository.save(reply);
    }

    @Transactional
    public void deleteReply(int id) {
        replyRepository.deleteById(id);
    }
}
