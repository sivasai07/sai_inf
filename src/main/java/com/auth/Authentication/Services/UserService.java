package com.auth.Authentication.Services;

import com.auth.Authentication.dto.LoginRequest;
import com.auth.Authentication.dto.RegisterRequest;
import com.auth.Authentication.entity.User;

public interface UserService {
    User registerUser(RegisterRequest request);

    User findUserByUsername(String username);

    User authenticateUser(LoginRequest request);

    // New Methods
}

