package com.mat.api.service;

import com.mat.api.core.payload.response.JwtResponse;
import com.mat.api.core.payload.request.LoginRequest;

public interface AuthService {
    JwtResponse loginUser(LoginRequest loginRequest);
}
