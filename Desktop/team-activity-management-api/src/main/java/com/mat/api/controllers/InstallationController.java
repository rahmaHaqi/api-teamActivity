package com.mat.api.controllers;


import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.InstallationDto;
import com.mat.api.service.InstallationService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/installation")
@Logging
public class InstallationController {
    private final InstallationService installationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<InstallationDto>> createInstallation(@RequestBody final InstallationDto installationDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                installationService.saveInstallation(installationDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<InstallationDto>> updateConfigurationReference(@PathVariable(name = "id") Long id,
                                                                                                @RequestBody final InstallationDto installationDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                installationService.updateInstallation(id, installationDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<InstallationDto>> deleteInstallationDto(@PathVariable(name = "id") Long id) {
        installationService.deleteInstallation(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<InstallationDto>>> getPagedListInstallation(@RequestParam(value = "page", required = true) Integer page,
                                                                                                            @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                installationService.getPagedListInstallationDTO(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<InstallationDto>> getInstallationById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                installationService.getInstallationById(id)), HttpStatus.OK);
    }
}



