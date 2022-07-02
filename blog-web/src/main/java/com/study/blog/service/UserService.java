package com.study.blog.service;

import com.study.blog.model.RoleEnum;
import com.study.blog.model.User;
import com.study.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public int join(User user) {
        validateDuplicateUser(user);

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);

        if (user.getRole() == null) {
            user.setRole(RoleEnum.USER);
        }

        userRepository.save(user);
        return user.getId();
    }

    public Optional<User> findUser(int id) {
        return userRepository.findById(id);
    }

    public void validateDuplicateUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public User updateUser(int id, User requestUser) {

        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        userRepository.save(user);

        return user;
    }

    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            System.out.println("사용자 삭제 완료, ID: " + id);

        } catch (Exception e) {
            throw new IllegalArgumentException("사용자 삭제 실패, ID: " + id);
        }

    }

}
