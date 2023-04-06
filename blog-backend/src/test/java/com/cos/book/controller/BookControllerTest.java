package com.cos.book.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cos.book.model.Book;
import com.cos.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@Slf4j
@WebMvcTest(controllers = com.cos.book.web.BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void save() throws Exception {
        // given (데이터 준비)
        Book book = new Book(null, "스프링 따라하기", "코스");
        String content = new ObjectMapper()
            .writeValueAsString(book);

        when(bookService.저장하기(book))
            .thenReturn(new Book(1L, "스프링 따라하기", "코스"));

        // when (테스트 실행)
        ResultActions resultAction = mockMvc.perform(post("/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
            .accept(MediaType.APPLICATION_JSON));

        //then (검증)
        resultAction
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value("스프링 따라하기"))
            .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void findAll() throws Exception {
        // given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링부트 따라하기", "코스"));
        books.add(new Book(2L, "리액트 따라하기", "코스"));

        when(bookService.모두가져오기())
            .thenReturn(books);

        // when
        ResultActions resultAction = mockMvc.perform(get("/book")
            .accept(MediaType.APPLICATION_JSON));

        // then
        resultAction
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(2)))
            .andExpect(jsonPath("$.[0].title").value("스프링부트 따라하기"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById() throws Exception {
        // given
        Long id = 1L;

        when(bookService.한건가져오기(id))
            .thenReturn(new Book(1L, "자바 공부하기", "쌀"));

        // when
        ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
            .accept(MediaType.APPLICATION_JSON));

        // then
        resultAction
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("자바 공부하기"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update() throws Exception {
        // given
        Long id = 1L;

        Book book = new Book(null, "C++ 따라하기", "코스");
        String content = new ObjectMapper()
            .writeValueAsString(book);

        when(bookService.수정하기(id, book))
            .thenReturn(new Book(1L, "C++ 따라하기", "코스"));

        // when
        ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
            .accept(MediaType.APPLICATION_JSON));

        //then (검증)
        resultAction
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("C++ 따라하기"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delete_test() throws Exception {
        // given
        Long id = 1L;

        when(bookService.삭제하기(id)).thenReturn("ok");

        // when
        ResultActions resultAction = mockMvc.perform(delete("/book/{id}", id)
            .accept(MediaType.TEXT_PLAIN));

        //then (검증)
        resultAction
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());

        MvcResult requestResult = resultAction.andReturn();
        String result = requestResult.getResponse().getContentAsString();

        Assertions.assertThat(result).isEqualTo("ok");
    }


}