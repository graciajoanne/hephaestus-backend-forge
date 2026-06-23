package com.fif.exercisespring.security;

import com.fif.exercisespring.exception.UnauthorizedException;

public class AuthUtil {
    public static String extractToken(String authorization) {
        if (authorization == null || authorization.isBlank()) {
            throw new UnauthorizedException("Authentication is required");
        }
        if (!authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authentication is required");
        }
        return authorization.substring(7);
    }
}
