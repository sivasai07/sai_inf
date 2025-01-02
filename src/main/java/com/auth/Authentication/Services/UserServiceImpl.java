package com.auth.Authentication.Services;

import com.auth.Authentication.dto.LoginRequest;
import com.auth.Authentication.dto.RegisterRequest;
import com.auth.Authentication.entity.User;
import com.auth.Authentication.Repository.RoleRepository;
import com.auth.Authentication.Repository.UserRepository;
import com.auth.Authentication.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new ApiException("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new ApiException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save roles as strings
        user.setRoles(Collections.singleton(request.getRole().toUpperCase()));

        return userRepository.save(user);
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User authenticateUser(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("Invalid username or password");
        }
        return user;
    }
}
