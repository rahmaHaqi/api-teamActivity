package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
//import com.mat.api.models.IssueLogEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto extends CustomDtoWithID {
    private String name;
    private String description;

    private String status;

    private LocalDate startDate;

    private LocalDate endDate;
    //private IssueLogEntity issueLog;
    private int deadlines;
    private List<Long> userIds;
    private List<UserDto> teams;
    private List<Long> configurationIds=new ArrayList<Long>();
    private List<ConfigurationDto> configurations=new ArrayList<ConfigurationDto>();
}


