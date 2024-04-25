package com.mat.api.service;


import com.mat.api.mapper.dto.ProjectDto;
import org.springframework.data.domain.Page;

public interface ProjectService {

    ProjectDto saveProject(ProjectDto project);

    ProjectDto getProjectById(Long id);
    ProjectDto updateProject(Long id, ProjectDto projectDto);


    void deleteProject(Long id);

    Page<ProjectDto> getPagedListProject(Integer page, Integer size);

}


