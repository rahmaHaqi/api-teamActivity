package com.mat.api.service.impl;


import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.CasTestDTO;
import com.mat.api.models.CasTestEntity;
import com.mat.api.repository.CasTestRepository;
import com.mat.api.service.CasTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class CasTestServiceImpl implements CasTestService {

    private final CasTestRepository casTestRepository;
    private final IMapClassWithDto mapper;


    @Override
    public CasTestDTO saveCasTest(CasTestDTO casTestDTO) {
        CasTestEntity casTest = new CasTestEntity();
        casTest.setName(casTestDTO.getName());
        /*casTest.setCode(casTestDTO.getCode());
        casTest.setGroupe(casTestDTO.getGroupe());
        casTest.setObjectif(casTestDTO.getObjectif());
        casTest.setResultatsAttendues(casTestDTO.getResultatsAttendues());
        casTest.setParametres(casTestDTO.getParametres());

         */

        casTestRepository.save(casTest);

        return mapper.convert(casTest, CasTestDTO.class);
    }

    @Override
    public CasTestDTO getCasTestById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        CasTestEntity casTest = casTestRepository.findById(id)
                .orElseThrow(() -> new BusinessException("casTest Not Found with id: " + id));
        return mapper.convert(casTest, CasTestDTO.class);
    }


    @Override
    public CasTestDTO updateCasTest(Long id, CasTestDTO casTestDTO) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }

        // Vérifie si la configuration existante avec l'ID donné existe
        CasTestEntity existingCasTest = casTestRepository.findById(id)
                .orElseThrow(() -> new BusinessException(CommonStatusCode.ID_IS_MISSING));

        // Mettre à jour les champs modifiables de la référence de configuration existante
        existingCasTest.setId(id);
        existingCasTest.setName(casTestDTO.getName());
        /*existingCasTest.setParametres(casTestDTO.getParametres());
        existingCasTest.setCode(casTestDTO.getResultatsAttendues());
        existingCasTest.setObjectif(casTestDTO.getObjectif());
        existingCasTest.setGroupe(casTestDTO.getGroupe());

         */


        // Assurez-vous que les autres champs éventuellement modifiables sont également mis à jour

        // Enregistrer les modifications
        CasTestEntity updatedCasTest = casTestRepository.save(existingCasTest);

        return mapper.convert(updatedCasTest, CasTestDTO.class);
    }

    @Override
    public void deleteCasTest(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        casTestRepository.deleteById(id);

    }

    @Override
    public Page<CasTestDTO> getPagedListCasTest(Integer page, Integer size) {
        page = (page == null || page < 1) ? 0 : page - 1;
        size = (size == null || size < 5) ? 5 : size;

        Pageable pageRequest = PageRequest.of(page, size);

        Page<CasTestEntity> pageOfEntities = null;

        pageOfEntities = casTestRepository.findAll(pageRequest);

        if (CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
        }


        return pageOfEntities.map(source -> mapper.convert(source, CasTestDTO.class));
    }
}
