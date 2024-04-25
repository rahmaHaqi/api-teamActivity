package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.IssueLogDto;
import com.mat.api.service.IssueLogService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issueLog")
@Logging
public class IssueLogController {
    private final IssueLogService issueLogService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBody<IssueLogDto>> createIssueLog(@RequestBody final IssueLogDto issueLogDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                issueLogService.saveIssueLog(issueLogDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<IssueLogDto>> updateIssueLog(@PathVariable(name = "id") Long id,
                                                                                                @RequestBody final IssueLogDto issueLogDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                issueLogService.updateIssueLog(id, issueLogDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<IssueLogDto>> deleteIssueLog(@PathVariable(name = "id") Long id) {
        issueLogService.deleteIssueLog(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<IssueLogDto>>> getPagedListIssueLog(@RequestParam(value = "page", required = true) Integer page,
                                                                                                            @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                issueLogService.getPagedListIssueLogDto(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<IssueLogDto>> getIssueLogById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                issueLogService.getIssueLogById(id)), HttpStatus.OK);
    }
}




