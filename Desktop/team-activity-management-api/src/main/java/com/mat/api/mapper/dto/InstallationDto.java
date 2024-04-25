package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import com.mat.api.models.ennum.StatusInstallation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallationDto extends CustomDtoWithID {
    private String nom;

    private LocalDate dateReception;

    private LocalDate dateInstallation;

    private StatusInstallation status;
    private Long project_id;

    //private String projectName;


}


