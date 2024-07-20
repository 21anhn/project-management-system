package com._anhn.services;

import com._anhn.config.JwtProvider;
import com._anhn.models.User;
import com._anhn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found!");
        }
        return user;
    }

    @Override
    public User findByUserId(Long UserId) throws Exception {
        Optional<User> user = userRepository.findById(UserId);
        if (user.isEmpty()) {
            throw new Exception("User not found!");
        }
        return user.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) throws Exception {
        user.setProjectSize(user.getProjectSize() + number);
        return userRepository.save(user);
    }
}
