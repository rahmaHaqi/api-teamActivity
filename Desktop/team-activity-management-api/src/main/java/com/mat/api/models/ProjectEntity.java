package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import com.mat.api.models.profiles.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity extends CustomAuditEntity {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "deadlines")
    private int deadlines;
    @ManyToMany
    @JoinTable(name = "users_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> teams;

    // needs RS oneToMany
    @OneToMany
    @JoinColumn(name = "configuration_id")
    private List<ConfigurationEntity> configurations;


}
