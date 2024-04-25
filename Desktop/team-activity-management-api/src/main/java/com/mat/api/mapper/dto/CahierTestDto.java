package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CahierTestDto extends CustomDtoWithID {

    private String nom;
    private String description;
    private Long project_id;
    private String projectName;
    private List<Long> cahierTestRefIds;
    private List<Long> casTestIds;
    private List<CahierTestRefDTO> referentiels;
    private List<CasTestDTO> casTests;

}
