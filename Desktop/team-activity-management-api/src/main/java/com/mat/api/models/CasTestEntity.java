package com.mat.api.models;

import com.mat.api.core.crudbasic.auditing.CustomAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
//@Builder
@Getter
@Setter
@Table(name = "CasTest")
@AllArgsConstructor
@NoArgsConstructor
public class CasTestEntity extends CustomAuditEntity {
   @Id
   @org.springframework.data.annotation.Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cas_test")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "casCode")
    private String casCode;

    @Column(name = "casObjectif")
    private String casObjectif;

    @Column(name = "casParametres")
    private String casParametres;

    @Column(name = "casGroupe")
    private String casGroupe;

    @Column(name = "resultatsAttendues")
    private String resultatsAttendues;

    @ManyToMany(mappedBy = "casTests")
    List<CahierTestEntity> cahierTests;







}


