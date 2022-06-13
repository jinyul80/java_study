package com.study.blog.service;

import com.study.blog.model.RoleEnum;
import com.study.blog.model.User;
import com.study.blog.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertThat(user.getUsername()).isEqualTo(findUser.getUsername());
    }

    @Test
    void updateUser() {
        User reqUser = new User();

        int id = 4;
        reqUser.setEmail("jini" + id + "@gmail.com");
        reqUser.setPassword("test5678");

        User user = userService.updateUser(id, reqUser);

        assertThat(reqUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(reqUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void updateUserFail() {
        User reqUser = new User();

        int id = 1;
        reqUser.setEmail("jini" + id + "@gmail.com");
        reqUser.setPassword("test5678");

        IllegalArgumentException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.updateUser(id, reqUser));

        assertThat(e.getMessage()).isEqualTo("수정에 실패하였습니다.");
    }

    @Test
    void deleteUser() {
        int id = 1;

        userService.deleteUser(id);

        Optional<User> opt = userService.findUser(id);
        assertThat(opt.isEmpty()).isEqualTo(true);
    }

    @Test
    void deleteUserFail() {
        int id = -1;

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(id));

        assertThat(e.getClass()).isEqualTo(IllegalArgumentException.class);

    }
}