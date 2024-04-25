package com.mat.api.service.impl;


import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.CahierTestRefDTO;
import com.mat.api.models.CahierTestRefEntity;
import com.mat.api.repository.CahierTestRefRepository;
import com.mat.api.service.CahierTestRefService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class CahierTestRefServiceImpl implements CahierTestRefService {

    private final CahierTestRefRepository cahierTestRefRepository;
    private final IMapClassWithDto mapper;


    @Override
    public CahierTestRefDTO saveCahierTestRef(CahierTestRefDTO cahierTestRefDTO) {
        CahierTestRefEntity cahierTestRefEntity = new CahierTestRefEntity();
        cahierTestRefEntity.setName(cahierTestRefDTO.getName());
        cahierTestRefEntity.setContent(cahierTestRefDTO.getContent());

        cahierTestRefRepository.save(cahierTestRefEntity);

        return mapper.convert(cahierTestRefEntity, CahierTestRefDTO.class);
    }


    @Override
    public CahierTestRefDTO getCahierTestRefById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        CahierTestRefEntity cahierTestRefEntity = cahierTestRefRepository.findById(id)
                .orElseThrow(() -> new BusinessException("cahierTest Not Found with id: " + id));
        return mapper.convert(cahierTestRefEntity, CahierTestRefDTO.class);
    }

    @Override
    public CahierTestRefDTO updateCahierTestRef(Long id, CahierTestRefDTO cahierTestRefDTO) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Vérifie si la configuration existante avec l'ID donné existe
        CahierTestRefEntity existingCahierTestRef = cahierTestRefRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.ID_IS_MISSING));

        // Mettre à jour les champs modifiables de la référence de configuration existante
        existingCahierTestRef.setName(cahierTestRefDTO.getName());
        existingCahierTestRef.setContent(cahierTestRefDTO.getContent());
        // Assurez-vous que les autres champs éventuellement modifiables sont également mis à jour

        // Enregistrer les modifications
        CahierTestRefEntity updatedCahierTestRef = cahierTestRefRepository.save(existingCahierTestRef);

        return mapper.convert(updatedCahierTestRef, CahierTestRefDTO.class);
    }

    @Override
    public void deleteCahierTestRef(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        cahierTestRefRepository.deleteById(id);

    }

    @Override
    public Page<CahierTestRefDTO> getPagedListCahierTestRef(Integer page, Integer size) {

        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<CahierTestRefEntity> pageOfEntities = null;

        pageOfEntities = cahierTestRefRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, CahierTestRefDTO.class));
    }
}
