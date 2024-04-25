package com.mat.api.service;

import com.mat.api.mapper.dto.CahierTestDto;
import org.springframework.data.domain.Page;

public interface CahierTestService {
    CahierTestDto saveCahierTest(CahierTestDto cahierTestDto);

    CahierTestDto  getCahierTestById(Long id);
    CahierTestDto  updateCahierTestDto(Long id, CahierTestDto cahierTestDto);


    void deleteCahierTest(Long id);
    Page<CahierTestDto> getPagedListCahierTest(Integer page, Integer size);
}
