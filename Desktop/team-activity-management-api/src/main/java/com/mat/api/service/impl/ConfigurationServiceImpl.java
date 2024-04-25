package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.ConfigurationDto;
import com.mat.api.models.ConfigurationEntity;
import com.mat.api.models.ConfigurationReferenceEntity;
import com.mat.api.models.ProjectEntity;
import com.mat.api.repository.ConfigurationReferenceRepository;
import com.mat.api.repository.ConfigurationRepository;
import com.mat.api.repository.ProjectRepository;
import com.mat.api.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;
    private final ProjectRepository projectRepository;
    private final ConfigurationReferenceRepository configurationReferenceRepository;
    private final IMapClassWithDto mapper;

    @Override
    public ConfigurationDto saveConfiguration(ConfigurationDto configurationDto) {
        //ConfigurationEntity configurationEntity = configurationRepository.findById(configurationDto.getProjectId());
        ProjectEntity projectEntity = projectRepository.findById(configurationDto.getProjectId())
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + configurationDto.getId()));

        List<ConfigurationReferenceEntity> configurationRefList = configurationReferenceRepository.findAllById(Collections.singleton(configurationDto.getConfigurationReferenceId()));
        ConfigurationEntity configurationEntity=mapper.convert(configurationDto, ConfigurationEntity.class);
        configurationEntity.setConfigurationReferences(configurationRefList);
        configurationEntity.setProject(projectEntity);
        configurationRepository.save(configurationEntity);

        return mapper.convert(configurationEntity,ConfigurationDto.class);
        /*projectRepository.save(project);*/
    }


    @Override
    public ConfigurationDto getConfigurationById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        ConfigurationEntity configurationEntity = configurationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + id));
        return mapper.convert(configurationEntity, ConfigurationDto.class);
        /*projectRepository.findById(id).orElse(null);*/
    }

    /*@Override
    public ConfigurationDto updateConfiguration(Long id, ConfigurationDto configurationDto) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        //ConfigurationEntity configurationEntity = configurationRepository.findById(configurationDto.getProjectId());
        ProjectEntity projectEntity = projectRepository.findById(configurationDto.getId())
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + configurationDto.getId()));
        ConfigurationEntity configurationEntity=mapper.convert(configurationDto, ConfigurationEntity.class);
        configurationEntity.setId(id);
        //configurationEntity.setConfigurationReference();
        configurationEntity.setProject(projectEntity);
        configurationRepository.save(configurationEntity);

        return mapper.convert(configurationEntity,ConfigurationDto.class);

    }

     */

    @Override
    public ConfigurationDto updateConfiguration(Long id, ConfigurationDto configurationDto) {
        // Vérifie si l'identifiant est valide
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Recherche du projet correspondant à l'identifiant fourni
        ProjectEntity projectEntity = projectRepository.findById(configurationDto.getProjectId())
                .orElseThrow(() -> new BusinessException("Project Not Found with id: " + configurationDto.getProjectId()));

        // Conversion de ConfigurationDto en ConfigurationEntity
        ConfigurationEntity configurationEntity = mapper.convert(configurationDto, ConfigurationEntity.class);

        // Attribution de l'identifiant et du projet à l'entité Configuration
        configurationEntity.setId(id);
        configurationEntity.setProject(projectEntity);

        // Sauvegarde de l'entité Configuration dans la base de données
        configurationRepository.save(configurationEntity);

        // Conversion de ConfigurationEntity en ConfigurationDto et retour
        return mapper.convert(configurationEntity, ConfigurationDto.class);
    }



    @Override
    public void deleteConfiguration(final Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        configurationRepository.deleteById(id);}



    @Override
    public Page<ConfigurationDto> getPagedListConfiguration(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<ConfigurationEntity> pageOfEntities = null;

        pageOfEntities = configurationRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, ConfigurationDto.class));
    }
}
