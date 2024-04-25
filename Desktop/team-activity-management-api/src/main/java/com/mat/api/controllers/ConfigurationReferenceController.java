package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.ConfigurationReferenceDTO;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.service.ConfigurationReferenceService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/configurationReference")
@Logging
public class ConfigurationReferenceController {
    private final ConfigurationReferenceService configurationReferenceService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<ConfigurationReferenceDTO>> createConfigurationReference(@RequestBody final ConfigurationReferenceDTO configurationReferenceDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationReferenceService.saveConfigurationReference(configurationReferenceDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<ConfigurationReferenceDTO>> updateConfigurationReference(@PathVariable(name = "id") Long id,
                                                                              @RequestBody final ConfigurationReferenceDTO configurationReferenceDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationReferenceService.updateConfigurationReference(id, configurationReferenceDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> deleteConfigurationReference(@PathVariable(name = "id") Long id) {
        configurationReferenceService.deleteConfigurationReferenceDTO(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<ConfigurationReferenceDTO>>> getPagedListConfigurationReference(@RequestParam(value = "page", required = true) Integer page,
                                                                                          @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationReferenceService.getPagedListConfigurationReferenceDTO(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<ConfigurationReferenceDTO>> getConfigurationReferenceById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                configurationReferenceService.getConfigurationReferenceById(id)), HttpStatus.OK);
    }


}

