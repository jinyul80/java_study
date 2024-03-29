package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // Page정보를 포함해서 리턴
    //    @GetMapping("/dummy/user/page")
    //    public Page<User> pageList(@PageableDefault(size=2, sort = "id", direction = Direction.DESC)
    //        Pageable pageable) {
    //        return userRepository.findAll(pageable);
    //    }

    // 실제 데이터 정보만 리턴
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size=2, sort = "id", direction = Direction.DESC)
    Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 유저는 없습니다. id: " + id));
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
        System.out.println(reqUser);

        User user = userRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 유저는 없습니다. id: " + id));

        user.setPassword(reqUser.getPassword());
        user.setEmail(reqUser.getEmail());

        // Transactional 어노테이션 사용하면 메서드 종료시 자동 commit 됨
        // save를 명시적으로 실행 할 필요 없음
        // userRepository.save(user);

        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제 실패하였습니다. 해당 id는 DB에 없습니다. id: " + id;
        }

        return "삭제되었습니다. id: "+ id;
    }
}
