package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.CahierTestDto;
import com.mat.api.service.CahierTestService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cahier_test")
@Logging
public class CahierTestController {
    private final CahierTestService cahierTestService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<CahierTestDto>> createCahierTest(@RequestBody final CahierTestDto cahierTestDto) {
        System.out.println(cahierTestDto.getNom());

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestService.saveCahierTest(cahierTestDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<CahierTestDto>> updateCahierTest(@PathVariable(name = "id") Long id,
                                                                                      @RequestBody final CahierTestDto cahierTestDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestService.updateCahierTestDto(id, cahierTestDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<CahierTestDto>> deleteCahierTest(@PathVariable(name = "id") Long id) {
        cahierTestService.deleteCahierTest(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<CahierTestDto>>> getPagedListInstallation(@RequestParam(value = "page", required = true) Integer page,
                                                                                        @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestService.getPagedListCahierTest(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<CahierTestDto>> getInstallationById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                cahierTestService.getCahierTestById(id)), HttpStatus.OK);
    }
}
