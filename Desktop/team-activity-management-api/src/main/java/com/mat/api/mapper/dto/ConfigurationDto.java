package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigurationDto extends CustomDtoWithID {

    private String name;

    private String content;

    private Long configurationReferenceId;

    private Long projectId;
    private ProjectDto project;
    /*private List<Long> configurationRefIds;
    private List<ConfigurationReferenceDTO> configurationReferences;

     */


}
