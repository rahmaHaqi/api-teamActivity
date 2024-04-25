package com.mat.api.service;

import com.mat.api.mapper.dto.InstallationDto;
import org.springframework.data.domain.Page;

public interface InstallationService {

    InstallationDto saveInstallation(InstallationDto installationDto);

    InstallationDto  getInstallationById(Long id);
    InstallationDto  updateInstallation(Long id, InstallationDto installationDto);


    void deleteInstallation(Long id);
    Page<InstallationDto> getPagedListInstallationDTO(Integer page, Integer size);

}

