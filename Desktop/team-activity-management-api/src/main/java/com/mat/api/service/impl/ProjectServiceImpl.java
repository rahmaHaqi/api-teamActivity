package com.mat.api.service.impl;


import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.ProjectDto;
import com.mat.api.models.ConfigurationEntity;
import com.mat.api.models.ProjectEntity;
import com.mat.api.models.profiles.UserEntity;
import com.mat.api.repository.ConfigurationRepository;
import com.mat.api.repository.ProjectRepository;
import com.mat.api.repository.UserRepository;
import com.mat.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {



    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final IMapClassWithDto mapper;
    private final ConfigurationRepository configurationRepository;

    @Override
    public ProjectDto saveProject(ProjectDto project) {
        List<UserEntity> users = userRepository.findAllById(project.getUserIds());
        List<ConfigurationEntity> configurations = configurationRepository.findAllById(project.getConfigurationIds());
        ProjectEntity projectEntity = mapper.convert(project, ProjectEntity.class);
        projectEntity.setId(project.getId());
        projectEntity.setTeams(users);
        projectEntity.setConfigurations(configurations);

        projectEntity = projectRepository.save(projectEntity);
        return mapper.convert(projectEntity, ProjectDto.class);
        /*projectRepository.save(project);*/
    }


    @Override
    public ProjectDto getProjectById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + id));
        return mapper.convert(projectEntity, ProjectDto.class);
        /*projectRepository.findById(id).orElse(null);*/
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        ProjectEntity existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + id));
        // Check if projectDto.getUserIds() is not null before querying the userRepository
        if (projectDto.getUserIds() != null) {
            List<UserEntity> users = userRepository.findAllById(projectDto.getUserIds());
            existingProject.setTeams(users);
        }
        // Update existingProject fields with new data
        existingProject.setName(projectDto.getName());
        existingProject.setDescription(projectDto.getDescription());
        existingProject.setStatus(projectDto.getStatus());
        existingProject.setStartDate(projectDto.getStartDate());
        existingProject.setEndDate(projectDto.getEndDate());
        existingProject.setDeadlines(projectDto.getDeadlines());

        // Save the updated project entity
        ProjectEntity updatedProject = projectRepository.save(existingProject);

        return mapper.convert(updatedProject, ProjectDto.class);
    }




    @Override
    public void deleteProject(final Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        projectRepository.deleteById(id);}



    @Override
    public Page<ProjectDto> getPagedListProject(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<ProjectEntity> pageOfEntities = null;

        pageOfEntities = projectRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, ProjectDto.class));
    }


}

