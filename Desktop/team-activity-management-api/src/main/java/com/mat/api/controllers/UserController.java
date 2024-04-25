package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.core.payload.request.RegisterRequest;
import com.mat.api.mapper.dto.UserDto;
import com.mat.api.service.UserService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Logging
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<UserDto>> createUser(@RequestBody final RegisterRequest registerRequest) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                userService.addUser(registerRequest)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<UserDto>> updateUser(@PathVariable(name = "id") Long id,
                                                            @RequestParam(name = "pass") boolean pass,
                                                            @RequestBody final RegisterRequest registerRequest) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                userService.updateUser(id,registerRequest, pass)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<UserDto>> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<UserDto>>> getPagedListUser(@RequestParam(value = "page", required = true) Integer page,
                                                                        @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                userService.getPagedListUser(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<UserDto>> getUserById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                userService.getUserById(id)), HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<ResponseBody<UserDto>> getCurrentUser() {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                userService.getCurrentUser()), HttpStatus.OK);
    }


}
