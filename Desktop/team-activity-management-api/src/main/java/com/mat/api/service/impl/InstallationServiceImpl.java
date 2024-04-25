package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.InstallationDto;
import com.mat.api.models.InstallationEntity;
import com.mat.api.models.ProjectEntity;
import com.mat.api.repository.InstallationRepository;
import com.mat.api.repository.ProjectRepository;
import com.mat.api.service.InstallationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class InstallationServiceImpl implements InstallationService {
    private final InstallationRepository installationRepository;
    private final ProjectRepository projectRepository;
    private final IMapClassWithDto mapper;
    /*@Override
    public InstallationDto saveInstallation(InstallationDto installationDto) {
        InstallationEntity installation =mapper.convert(installationDto, InstallationEntity.class);
        ProjectEntity project = projectRepository.findById(installationDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + installationDto.getProject_id()));

        installation.setProject(project);
        installationRepository.save(installation);
        return mapper.convert(installation, InstallationDto.class);
    }

     */
    @Override
    public InstallationDto saveInstallation(InstallationDto installationDto) {
        // Vérifie si l'identifiant du projet est valide
        if (installationDto.getProject_id() == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Recherche du projet correspondant à l'identifiant fourni
        ProjectEntity project = projectRepository.findById(installationDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + installationDto.getProject_id()));

        // Conversion de InstallationDto en InstallationEntity
        InstallationEntity installation = mapper.convert(installationDto, InstallationEntity.class);

        // Attribution du projet à l'entité InstallationEntity
        installation.setProject(project);

        // Sauvegarde de l'entité InstallationEntity dans la base de données
        installationRepository.save(installation);

        // Conversion de l'InstallationEntity en InstallationDto et retour
        return mapper.convert(installation, InstallationDto.class);
    }



    @Override
    public InstallationDto getInstallationById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        InstallationEntity installation = installationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("ConfigurationReference Not Found with id: " + id));
        return mapper.convert(installation, InstallationDto.class);
    }

    /*@Override
    public InstallationDto updateInstallation(Long id, InstallationDto installationDto) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        //ConfigurationEntity configurationEntity = configurationRepository.findById(configurationDto.getProjectId());
        InstallationEntity installation=mapper.convert(installationDto, InstallationEntity.class);

        installationRepository.save(installation);
        return mapper.convert(installation, InstallationDto.class);
    }

     */
    @Override
    public InstallationDto updateInstallation(Long id, InstallationDto installationDto) {
        // Vérifie si l'identifiant est valide
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Recherche de l'entité Installation correspondant à l'identifiant fourni
        InstallationEntity installation = installationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Installation not found with ID: " + id));

        // Conversion de l'InstallationDto en InstallationEntity
        mapper.convert(installationDto, InstallationEntity.class);

        // Recherche du projet correspondant à l'identifiant fourni dans le DTO
        ProjectEntity project = projectRepository.findById(installationDto.getProject_id())
                .orElseThrow(() -> new BusinessException("Project not found with ID: " + installationDto.getProject_id()));

        // Attribution du projet à l'entité Installation
        installation.setProject(project);

        // Sauvegarde de l'entité Installation dans la base de données
        installationRepository.save(installation);

        // Conversion de l'InstallationEntity en InstallationDto et retour
        return mapper.convert(installation, InstallationDto.class);
    }


    @Override
    public void deleteInstallation(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        installationRepository.deleteById(id);

    }

    @Override
    public Page<InstallationDto> getPagedListInstallationDTO(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<InstallationEntity> pageOfEntities = null;

        pageOfEntities = installationRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, InstallationDto.class));
    }


}


