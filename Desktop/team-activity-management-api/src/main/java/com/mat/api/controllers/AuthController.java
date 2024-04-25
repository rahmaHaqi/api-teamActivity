package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.core.payload.response.JwtResponse;
import com.mat.api.core.payload.request.LoginRequest;
import com.mat.api.service.AuthService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Logging
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<JwtResponse>> authenticateUser(@RequestBody final LoginRequest loginRequest) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                authService.loginUser(loginRequest)), HttpStatus.OK);
    }


}
