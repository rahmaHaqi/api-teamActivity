package com.mat.api.core.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class LoginRequest {

    @NotBlank(message = "the login is null")
    private String username;
    @NotBlank(message = "the password is null")
    private String password;
}
