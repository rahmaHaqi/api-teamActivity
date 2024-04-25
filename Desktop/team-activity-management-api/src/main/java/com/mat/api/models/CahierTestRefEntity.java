package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "CahierTestRefs")
@AllArgsConstructor
@NoArgsConstructor
public class CahierTestRefEntity extends CustomAuditEntity {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany(mappedBy = "referentiels")
    List<CahierTestEntity> cahierTests;





}


