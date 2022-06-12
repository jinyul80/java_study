package com.study.blog.service;

import com.study.blog.model.User;
import com.study.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int join(User user) {
        userRepository.save(user);
        return user.getId();
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

}
