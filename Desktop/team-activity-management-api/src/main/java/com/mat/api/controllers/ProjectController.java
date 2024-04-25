package com.mat.api.controllers;

import com.mat.api.core.ResponseBody;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.service.ProjectService;
import io.github.logger.controller.annotation.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@Logging
public class ProjectController {

    private final ProjectService projectService;

        @PostMapping("/create")
        public ResponseEntity<ResponseBody<ProjectDto>> createProject(@RequestBody final ProjectDto projectDto) {
            return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                    projectService.saveProject(projectDto)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> updateProject(@PathVariable(name = "id") Long id,
                                                            @RequestBody final ProjectDto projectDto) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                projectService.updateProject(id, projectDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> deleteProject(@PathVariable(name = "id") Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(new ResponseBody<>(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paged-list")
    public ResponseEntity<ResponseBody<Page<ProjectDto>>> getPagedListProject(@RequestParam(value = "page", required = true) Integer page,
                                                                        @RequestParam(value = "size", required = true) Integer size) {
        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                projectService.getPagedListProject(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<ProjectDto>> getProjectById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(new ResponseBody<>(CommonStatusCode.SUCCESS,
                projectService.getProjectById(id)), HttpStatus.OK);
    }


}