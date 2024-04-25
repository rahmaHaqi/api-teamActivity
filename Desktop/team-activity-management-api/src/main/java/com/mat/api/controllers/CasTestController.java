package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.CasTestDTO;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.service.CasTestService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/casTest")
@Logging
public class CasTestController {
    private final CasTestService casTestService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<CasTestDTO>> createCasTest(@RequestBody final CasTestDTO casTestDTO ) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                casTestService.saveCasTest(casTestDTO)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<CasTestDTO>> updateCasTest(@PathVariable(name = "id") Long id,
                                                                              @RequestBody final CasTestDTO casTestDTO) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                casTestService.updateCasTest(id, casTestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> deleteCasTest(@PathVariable(name = "id") Long id) {
        casTestService.deleteCasTest(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<CasTestDTO>>> getPagedListCasTest(@RequestParam(value = "page", required = true) Integer page,
                                                                                          @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                casTestService.getPagedListCasTest(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<CasTestDTO>> getCasTestById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                casTestService.getCasTestById(id)), HttpStatus.OK);
    }


}

