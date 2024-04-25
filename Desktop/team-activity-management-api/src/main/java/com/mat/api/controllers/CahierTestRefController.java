package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.CahierTestRefDTO;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.service.CahierTestRefService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cahierTestRef")
@Logging
public class CahierTestRefController {
    private final CahierTestRefService cahierTestRefService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<CahierTestRefDTO>> createCahierTestRef(@RequestBody final CahierTestRefDTO cahierTestRefDTO) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestRefService.saveCahierTestRef(cahierTestRefDTO)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<CahierTestRefDTO>> updateCahierTestRef(@PathVariable(name = "id") Long id,
                                                                              @RequestBody final CahierTestRefDTO cahierTestRefDTO) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestRefService.updateCahierTestRef(id, cahierTestRefDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> deleteConfigurationReference(@PathVariable(name = "id") Long id) {
        cahierTestRefService.deleteCahierTestRef(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<CahierTestRefDTO>>> getPagedListCahierTestRef(@RequestParam(value = "page", required = true) Integer page,
                                                                                          @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestRefService.getPagedListCahierTestRef(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<CahierTestRefDTO>> getCahierTestRefById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestRefService.getCahierTestRefById(id)), HttpStatus.OK);
    }


}

