package com.mat.api.core.payload.response;

import com.mat.api.mapper.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long expiresIn;
    private UserDto user;
    private List<String> roles;
}
