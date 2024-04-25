package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.core.errorhandling.exeption.ErrorCode;
import com.mat.api.core.payload.response.JwtResponse;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.UserDto;
import com.mat.api.models.profiles.UserEntity;
import com.mat.api.service.UserService;
import com.mat.api.core.payload.request.LoginRequest;
import com.mat.api.core.security.jwt.JwtUtils;
import com.mat.api.core.security.services.UserDetailsImpl;
import com.mat.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final IMapClassWithDto mapper;

    @Value("${authentication.jwt.expiration}")
    private Long expiresIn;

    @Override
    public JwtResponse loginUser(final LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            throw new BusinessException(ErrorCode.LOGIN_OR_PASSWORD_MUST_BE_NOT_NULL);
        }

        Authentication authentication = null;
        try {
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword());
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (final AuthenticationException e) {
            throw new BusinessException(CommonStatusCode.INCORRECT_CREDENTIALS);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = jwtUtils.generateToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final UserEntity userEntity = userService.getUserByUserName(loginRequest.getUsername());

        final UserDto userDto = mapper.convert(userEntity,UserDto.class);
        return JwtResponse.builder()
                .type("Bearer")
                .token(jwt)
                .user(userDto)
                .expiresIn(expiresIn)
                .roles(roles)
                .build();

    }
}
