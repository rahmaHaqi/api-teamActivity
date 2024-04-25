package com.mat.api.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@Table(name = "CahierTest")
@AllArgsConstructor
@NoArgsConstructor
public class CahierTestEntity {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;


    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cahierTest_referenceID",
            joinColumns = @JoinColumn(name = "cahier_test_id"),
            inverseJoinColumns = @JoinColumn(name = "cahier_testRef_id"))
    private List<CahierTestRefEntity> referentiels;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "casTestID",
            joinColumns = @JoinColumn(name = "cahier_test_id"),
            inverseJoinColumns = @JoinColumn(name = "cas_test_id"))
    private List<CasTestEntity> casTests;


}
