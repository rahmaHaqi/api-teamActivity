package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import com.mat.api.models.ennum.StatusInstallation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Table(name = "Installations")
@AllArgsConstructor
@NoArgsConstructor

public class InstallationEntity extends CustomAuditEntity {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "dateReception")
    private LocalDate dateReception;

    @Column(name = "dateInstallation")
    private LocalDate dateInstallation;

    @Column(name = "status")
    private StatusInstallation status;


    @OneToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;


}


