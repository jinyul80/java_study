package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(@RequestBody User user) {
        System.out.println(user.toString());
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원 가입이 완료되었습니다.";
    }

    @GetMapping("/dummy/user")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 유저는 없습니다. id: " + id));
    }
}
