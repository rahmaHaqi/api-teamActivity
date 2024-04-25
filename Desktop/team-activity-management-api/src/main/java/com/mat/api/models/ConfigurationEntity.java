package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "configurations")
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationEntity extends CustomAuditEntity {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(name = " Configuration_references",
            joinColumns = @JoinColumn(name = "configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "configurationReference_id"))
    private List<ConfigurationReferenceEntity> configurationReferences;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

}
