package com.example.banking.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for security operations.
 */
public class SecurityUtils {
    /**
     * Get the username of the currently authenticated user.
     *
     * @return The username of the authenticated user, or "anonymous" if no user is authenticated
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "anonymous";
        }
        return authentication.getName();
    }
}
