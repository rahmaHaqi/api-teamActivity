package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigurationReferenceDTO extends CustomDtoWithID {

    private String name;
    private String content;
    private Long configurationId;


}
