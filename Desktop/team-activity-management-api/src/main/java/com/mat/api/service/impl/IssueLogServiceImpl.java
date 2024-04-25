package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.IssueLogDto;
import com.mat.api.models.IssueLogEntity;
import com.mat.api.models.ProjectEntity;
import com.mat.api.repository.IssueLogRepository;
import com.mat.api.repository.ProjectRepository;
import com.mat.api.service.IssueLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class IssueLogServiceImpl implements IssueLogService {

    private final IssueLogRepository issueLogRepository;
    private final IMapClassWithDto mapper;
    private final ProjectRepository projectRepository;

    /*@Override
    public IssueLogDto saveIssueLog(IssueLogDto issueLogDto) {
        IssueLogEntity issueLog =mapper.convert(issueLogDto, IssueLogEntity.class);
        ProjectEntity project = projectRepository.findById(issueLogDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + issueLogDto.getProject_id()));

        issueLog.setProject(project);
        issueLogRepository.save(issueLog);
        return mapper.convert(issueLog, IssueLogDto.class);
    }

     */
    @Override
    public IssueLogDto saveIssueLog(IssueLogDto issueLogDto) {
        // Vérifie si l'identifiant du projet est valide
        if (issueLogDto.getProject_id() == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Recherche du projet correspondant à l'identifiant fourni
        ProjectEntity project = projectRepository.findById(issueLogDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + issueLogDto.getProject_id()));

        // Conversion de IssueLogDto en IssueLogEntity
        IssueLogEntity issueLog = mapper.convert(issueLogDto, IssueLogEntity.class);

        // Attribution du projet à l'entité IssueLogEntity
        issueLog.setProject(project);

        // Sauvegarde de l'entité IssueLogEntity dans la base de données
        issueLogRepository.save(issueLog);

        // Conversion de l'IssueLogEntity en IssueLogDto et retour
        return mapper.convert(issueLog, IssueLogDto.class);
    }


    @Override
    public IssueLogDto getIssueLogById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        IssueLogEntity issueLog = issueLogRepository.findById(id)
                .orElseThrow(() -> new BusinessException("ConfigurationReference Not Found with id: " + id));
        return mapper.convert(issueLog, IssueLogDto.class);
    }

    @Override
    public IssueLogDto updateIssueLog(Long id, IssueLogDto issueLogDto) {
        // Vérifie si l'identifiant est valide
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Recherche de l'entité IssueLog correspondant à l'identifiant fourni
        IssueLogEntity issueLog = issueLogRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Issue Log not found with ID: " + id));

        // Conversion de l'IssueLogDto en IssueLogEntity
        mapper.convert(issueLogDto, IssueLogEntity.class);

        // Recherche du projet correspondant à l'identifiant fourni dans le DTO
        ProjectEntity project = projectRepository.findById(issueLogDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + issueLogDto.getProject_id()));

        // Attribution du projet à l'entité IssueLog
        issueLog.setProject(project);

        // Sauvegarde de l'entité IssueLog dans la base de données
        issueLogRepository.save(issueLog);

        // Conversion de l'IssueLogEntity en IssueLogDto et retour
        return mapper.convert(issueLog, IssueLogDto.class);
    }


    @Override
    public void deleteIssueLog(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        issueLogRepository.deleteById(id);

    }

    @Override
    public Page<IssueLogDto> getPagedListIssueLogDto(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<IssueLogEntity> pageOfEntities = null;

        pageOfEntities = issueLogRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, IssueLogDto.class));
    }
}