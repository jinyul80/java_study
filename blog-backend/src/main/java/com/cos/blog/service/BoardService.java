package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
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

}
