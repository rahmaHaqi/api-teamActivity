package com.mat.api.service;

import com.mat.api.core.payload.request.RegisterRequest;
import com.mat.api.mapper.dto.UserDto;
import com.mat.api.models.profiles.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto addUser(RegisterRequest registerRequest);

    UserDto getCurrentUser();

    Boolean existsByUsername(String username);

    UserDto updateUser(Long id, RegisterRequest registerRequest, boolean pass);

    void deleteUser(Long id);

    UserEntity getUserByUserName(String loginOrUsername);

    Page<UserDto> getPagedListUser(Integer page, Integer size);

    UserDto getUserById(Long id);


}
