package com.mat.api.mapper.dto;

import com.mat.api.core.crudbasic.mapper.dto.CustomDtoWithID;
import com.mat.api.models.ennum.Role;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends CustomDtoWithID {
    private String username;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    //private List<Long> roleIds;

}
