package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import com.mat.api.models.ennum.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@Table(name = "IssueLogs")
@AllArgsConstructor
@NoArgsConstructor
public class IssueLogEntity extends CustomAuditEntity {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dateDetection")
    private LocalDate dateDetection;


    @Column(name = "anomalie")
    private Boolean anomaly;


    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status status;


    @OneToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;


}


