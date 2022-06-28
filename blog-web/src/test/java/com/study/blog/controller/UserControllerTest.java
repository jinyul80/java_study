package com.study.blog.controller;

import com.study.blog.dto.ResponseDto;
import com.study.blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    void login() {

        var user = new User();
        user.setUsername("jini");
        user.setPassword("1234");

        ResponseDto<Integer> result = userController.login(user);

        assertThat(result.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void loginFail() {

        var user = new User();
        user.setUsername("jini");
        user.setPassword("1");

        ResponseDto<Integer> result = userController.login(user);

        assertThat(result.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}