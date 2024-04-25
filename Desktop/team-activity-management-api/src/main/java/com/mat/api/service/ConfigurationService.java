package com.mat.api.service;


import com.mat.api.mapper.dto.ConfigurationDto;
import org.springframework.data.domain.Page;

public interface ConfigurationService {

    ConfigurationDto saveConfiguration(ConfigurationDto configurationDto);

    ConfigurationDto  getConfigurationById(Long id);
    ConfigurationDto  updateConfiguration(Long id, ConfigurationDto configurationDto);


    void deleteConfiguration(Long id);

    Page<ConfigurationDto > getPagedListConfiguration(Integer page, Integer size);

}
