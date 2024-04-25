package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import com.mat.api.models.ennum.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueLogDto extends CustomDtoWithID {
    private LocalDate dateDetection;

    private Boolean anomaly;

    private String description;

    private Status status;

    private Long project_id;

    //private String projectName;
}


