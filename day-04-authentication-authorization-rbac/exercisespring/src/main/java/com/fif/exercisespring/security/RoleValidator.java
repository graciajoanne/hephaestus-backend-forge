package com.fif.exercisespring.security;

import java.util.Arrays;

import com.fif.exercisespring.exception.ForbiddenException;

public class RoleValidator {
    public static void hasAnyRole(String userRole,String... allowedRoles) {
        boolean allowed = Arrays.asList(allowedRoles).contains(userRole);
        if (!allowed) {
            throw new ForbiddenException("You do not have permission to access this resource");
        }
    }
}