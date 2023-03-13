package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int join(User user){
        try {
            user.setRole(RoleType.USER);
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService join(): " + e.getMessage());
        }

        return -1;
    }

}
