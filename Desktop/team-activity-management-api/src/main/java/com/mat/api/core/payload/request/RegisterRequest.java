package com.mat.api.core.payload.request;

import com.mat.api.models.ennum.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enable;
    private String password;
    private Role role;
}
