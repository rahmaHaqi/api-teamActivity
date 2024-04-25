package com.mat.api.service;


import com.mat.api.mapper.dto.CasTestDTO;
import org.springframework.data.domain.Page;


public interface CasTestService {
    CasTestDTO saveCasTest(CasTestDTO casTestDTO);

    CasTestDTO getCasTestById(Long id);
    CasTestDTO updateCasTest(Long id, CasTestDTO casTestDTO );


    void deleteCasTest(Long id);

    Page<CasTestDTO> getPagedListCasTest(Integer page, Integer size);

}
