package com.maximys777.authapi.dto.request;

public record AuthRequest(
        String email,
        String password
) {
}
