package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.ConfigurationDto;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.service.ConfigurationService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/configuration")
@Logging
public class ConfigurationController {
    private final ConfigurationService configurationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<ConfigurationDto>> createConfiguration(@RequestBody final ConfigurationDto configurationDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationService.saveConfiguration(configurationDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<ConfigurationDto>> updateConfiguration(@PathVariable(name = "id") Long id,
                                                                  @RequestBody final ConfigurationDto configurationDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationService.updateConfiguration(id, configurationDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> deleteConfiguration(@PathVariable(name = "id") Long id) {
        configurationService.deleteConfiguration(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<ConfigurationDto>>> getPagedListConfiguration(@RequestParam(value = "page", required = true) Integer page,
                                                                              @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationService.getPagedListConfiguration(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<ConfigurationDto>> getConfigurationById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationService.getConfigurationById(id)), HttpStatus.OK);
    }



}
