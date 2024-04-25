package com.mat.api.service;

import com.mat.api.mapper.dto.ConfigurationReferenceDTO;
import org.springframework.data.domain.Page;

public interface ConfigurationReferenceService {
    ConfigurationReferenceDTO saveConfigurationReference(ConfigurationReferenceDTO configurationReferenceDTO);

    ConfigurationReferenceDTO getConfigurationReferenceById(Long id);
    ConfigurationReferenceDTO updateConfigurationReference(Long id, ConfigurationReferenceDTO configurationReferenceDTO );


    void deleteConfigurationReferenceDTO(Long id);

    Page<ConfigurationReferenceDTO> getPagedListConfigurationReferenceDTO(Integer page, Integer size);

}
