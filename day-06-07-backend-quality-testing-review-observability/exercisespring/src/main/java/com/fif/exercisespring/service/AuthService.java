package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.LoginRequest;
import com.fif.exercisespring.dto.LoginResponse;
import com.fif.exercisespring.dto.UserResponse;
// import com.fif.exercisespring.dto.UserResponse;
import com.fif.exercisespring.exception.UnauthorizedException;
import com.fif.exercisespring.model.User;

import org.springframework.stereotype.Service;

// import java.util.ArrayList;
import java.util.HashMap;
// import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("admin", new User(
                        "admin",
                        "admin123",
                        "ADMIN",
                        "token-admin"));

        users.put("staff", new User(
                        "staff",
                        "staff123",
                        "STAFF",
                        "token-staff"));

        users.put("approver", new User(
                        "approver",
                        "approver123",
                        "APPROVER",
                        "token-approver"));

        users.put("manager", new User(
                        "manager",
                        "manager123",
                        "MANAGER",
                        "token-manager"));
        
    }

    public LoginResponse login(LoginRequest request) {
        User user = users.get(request.getUsername());
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("Invalid username or password");
        }
        return new LoginResponse(user.getToken(),user.getUsername(),user.getRole()
        );
    }

    public User getUserByToken (String token) {
        for(User user : users.values()) {
            if(user.getToken().equals(token)) {
                return user;
            }
        }
        throw new UnauthorizedException("Invalid Token");
    }

    public UserResponse me(String token) {
        User user = getUserByToken(token);
        return new UserResponse(user.getUsername(),user.getRole());
    }

    public String getRoleByToken(String token) {
        User user = getUserByToken(token);
        return user.getRole();
    }
}