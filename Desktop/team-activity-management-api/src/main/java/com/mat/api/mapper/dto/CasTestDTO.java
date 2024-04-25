package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CasTestDTO extends CustomDtoWithID {

    private String name;

    private String code;

    private String Objectif;

    private String Parametres;


    private String Groupe;

    private String ResultatsAttendues;

}
