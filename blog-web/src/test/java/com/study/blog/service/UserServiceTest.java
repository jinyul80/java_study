package com.study.blog.service;

import com.study.blog.model.RoleEnum;
import com.study.blog.model.User;
import com.study.blog.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void join() {
        User user = new User();
        user.setUsername("jini");
        user.setPassword("123456");
        user.setEmail("jini@gmail.com");
        user.setRole(RoleEnum.USER);

        int id = userService.join(user);

        User findUser = userRepository.findById(id).get();

        Assertions.assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }
}