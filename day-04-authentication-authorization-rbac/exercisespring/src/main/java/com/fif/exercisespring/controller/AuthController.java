package com.fif.exercisespring.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fif.exercisespring.dto.LoginRequest;
import com.fif.exercisespring.dto.LoginResponse;
import com.fif.exercisespring.dto.UserResponse;
// import com.fif.exercisespring.model.User;
import com.fif.exercisespring.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@Tag(name = "Authentication API",description = "Authentication and Authorization API")
public class AuthController {
    private final AuthService authService;
    

    // public AuthController(AuthService authService) {
    //     this.authService = authService;
    // }

    @Operation(summary = "Login User")
    @ApiResponse(responseCode = "200", description = "Customer success")
    @ApiResponse(responseCode = "401", description = "Invalid username or password")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // @Operation(summary = "Get Current User")
    // @ApiResponse(responseCode = "200", description = "Current user found")
    // @ApiResponse(responseCode = "401", description = "Invalid token")
    // @GetMapping("/me")
    // public ResponseEntity<UserResponse> me(@RequestHeader("Authorization") String authorization) {
    //     //// String token = authorization.replace("Bearer ", "");
    //     String token = authorization.substring(7);
    //     // User user = authService.getUserByToken(token);
    //     //// UserResponse response = new UserResponse(user.getUsername(),user.getRole());
    //     UserResponse response = authService.me(token);
    //     return ResponseEntity.ok(response);
    // }

    @Operation(summary = "Get Current User")
    @ApiResponse(responseCode = "200", description = "Current user information")
    @ApiResponse(responseCode = "401", description = "Invalid token")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        UserResponse response = authService.me(token);
        return ResponseEntity.ok(response);
    }
}