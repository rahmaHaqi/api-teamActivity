/*package com.mat.api.models.profiles;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import com.mat.api.models.ennum.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class RoleEntity extends CustomAuditEntity implements Serializable {
    private static final long serialVersionUID = 167338507135474088L;

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "role_id_seq",
            sequenceName = "role_id_seq",
            allocationSize = 1,
            initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_id_seq")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;

    @Column(name = "description")
    private String description;

}*/
