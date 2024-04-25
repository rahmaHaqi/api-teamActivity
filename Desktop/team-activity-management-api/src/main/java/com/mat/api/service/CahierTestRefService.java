package com.mat.api.service;


import com.mat.api.mapper.dto.CahierTestRefDTO;
import org.springframework.data.domain.Page;



public interface CahierTestRefService  {
    CahierTestRefDTO saveCahierTestRef(CahierTestRefDTO cahierTestRefDTO);

    CahierTestRefDTO getCahierTestRefById(Long id);
    CahierTestRefDTO updateCahierTestRef(Long id, CahierTestRefDTO cahierTestRefDTO );


    void deleteCahierTestRef(Long id);

    Page<CahierTestRefDTO> getPagedListCahierTestRef(Integer page, Integer size);

}
