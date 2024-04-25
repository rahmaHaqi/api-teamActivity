package com.mat.api.service.impl;

import com.mat.api.core.errorhandling.businessexeption.BusinessException;
import com.mat.api.core.errorhandling.exeption.CommonStatusCode;
import com.mat.api.mapper.converter.IMapClassWithDto;
import com.mat.api.mapper.dto.CahierTestDto;
import com.mat.api.models.CahierTestEntity;
import com.mat.api.models.CahierTestRefEntity;
import com.mat.api.models.CasTestEntity;
import com.mat.api.models.ProjectEntity;
import com.mat.api.repository.CahierTestRefRepository;
import com.mat.api.repository.CahierTestRepository;
import com.mat.api.repository.CasTestRepository;
import com.mat.api.repository.ProjectRepository;
import com.mat.api.service.CahierTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CahierTestServiceImpl implements CahierTestService {
    private final CahierTestRepository cahierTestRepository;
    private final ProjectRepository projectRepository;
    private final IMapClassWithDto mapper;
    private final CahierTestRefRepository cahierTestRefRepository;
    private final CasTestRepository casTestRepository;


    @Override
    public CahierTestDto saveCahierTest(CahierTestDto cahierTestDto) {
        try {
            System.out.println(cahierTestDto);
            List<CahierTestRefEntity> referentiels = cahierTestRefRepository.findAllById(cahierTestDto.getCahierTestRefIds());
            System.out.println(cahierTestDto.getCasTests());
            List<CasTestEntity> casTests = new ArrayList<>();
            cahierTestDto.getCasTests().forEach(cas -> {
                casTests.add(casTestRepository.findById(cas.getId()).get());
            });

            CahierTestEntity cahierTest = mapper.convert(cahierTestDto, CahierTestEntity.class);
            ProjectEntity project = projectRepository.findById(cahierTestDto.getProject_id())
                    .orElseThrow(() -> new BusinessException("Project not found with ID: " + cahierTestDto.getProject_id()));
            cahierTest.setReferentiels(referentiels);
            cahierTest.setProject(project);
            cahierTest.setCasTests(casTests
            );


            CahierTestEntity savedCahierTest = cahierTestRepository.save(cahierTest);

            CahierTestDto savedCahierTestDto = mapper.convert(savedCahierTest, CahierTestDto.class);

            return savedCahierTestDto;
        } catch (Exception e) {
            throw new BusinessException("Erreur lors de l'enregistrement du CahierTest : " + e.getMessage());
        }
    }


    @Override
    public CahierTestDto getCahierTestById(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        CahierTestEntity cahierTest = cahierTestRepository.findById(id)
                .orElseThrow(() -> new BusinessException("cahierTest Not Found with id: " + id));
        return mapper.convert(cahierTest, CahierTestDto.class);
    }

    @Override
    public CahierTestDto updateCahierTestDto(Long id, CahierTestDto cahierTestDto) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        //ConfigurationEntity configurationEntity = configurationRepository.findById(configurationDto.getProjectId());
        CahierTestEntity cahierTest = mapper.convert(cahierTestDto, CahierTestEntity.class);

        if (cahierTestDto.getCahierTestRefIds() != null) {
            List<CahierTestRefEntity> referentiels = cahierTestRefRepository.findAllById(cahierTestDto.getCahierTestRefIds());
            cahierTest.setReferentiels(referentiels);
        }


        if (cahierTestDto.getCasTestIds() != null) {
            List<CasTestEntity> casTests = casTestRepository.findAllById(cahierTestDto.getCasTestIds());
            //cahierTest.setCasTests(casTests);
        }


        // Update existingProject fields with new data
        cahierTest.setId(id);
        cahierTest.setNom(cahierTestDto.getNom());
        cahierTest.setDescription(cahierTestDto.getDescription());

        cahierTestRepository.save(cahierTest);
        return mapper.convert(cahierTest, CahierTestDto.class);
    }

    @Override
    public void deleteCahierTest(Long id) {
        if (id == null) {
            throw new BusinessException(CommonStatusCode.ID_IS_MISSING);
        }
        cahierTestRepository.deleteById(id);

    }

    @Override
    public Page<CahierTestDto> getPagedListCahierTest(Integer page, Integer size) {
        try {
            page = (page == null || page < 1) ? 0 : page - 1;
            size = (size == null || size < 5) ? 5 : size;

            Pageable pageRequest = PageRequest.of(page, size);

            Page<CahierTestEntity> pageOfEntities = cahierTestRepository.findAll(pageRequest);

            if (!pageOfEntities.hasContent()) {
                throw new BusinessException(CommonStatusCode.NO_CONTENT_EXCEPTION);
            }

            return pageOfEntities.map(source -> {
                CahierTestDto result = mapper.convert(source, CahierTestDto.class);
                result.setProject_id(source.getProject() != null ? source.getProject().getId() : null);
                result.setProjectName(source.getProject() != null ? source.getProject().getName() : null);
                return result;
            });
        } catch (DataAccessException e) {
            throw new BusinessException("Erreur d'accès aux données lors de la récupération des CahierTests : " + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Une erreur est survenue lors de la récupération des CahierTests : " + e.getMessage());
        }
    }

}


