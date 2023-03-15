package com.cos.blog.service;

import static org.assertj.core.api.Assertions.*;

import com.cos.blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void join() {
        User user = User.builder()
                        .username("jini")
                        .password("5762")
                        .email("jini@gmail.com")
                        .build();

        int result = userService.join(user);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @Transactional
    void 중복등록() {
        User user1 = User.builder()
                         .username("jini")
                         .password("5762")
                         .email("jini@gmail.com")
                         .build();

        User user2 = User.builder()
                         .username("jini")
                         .password("5762")
                         .email("jini@gmail.com")
                         .build();
        userService.join(user1);

        int result = userService.join(user2);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void login() {
        User user = User.builder()
                        .username("meta")
                        .password("m1234")
                        .build();

        User result = userService.login(user);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
    }
}