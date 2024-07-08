package com.ranine.rls.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "statrecapchaine")
public class Statrecapchaine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDStatRecapChaine")
    private Long iDStatRecapChaine;

    @Column(name = "IDChaineMontage")
    private Integer iDChaineMontage;

    @Column(name = "IDEmploye")
    private Integer iDEmploye;

    @Column(name = "Quantite")
    private Integer quantite;

    @Column(name = "TotalTpsTravail")
    private Double totalTpsTravail;

    @Lob
    @Column(name = "Date")
    private String date;

    @Column(name = "Rndmt")
    private Double rndmt;

    @Column(name = "IDOperation")
    private Integer iDOperation;

    @Column(name = "IDFamille")
    private Integer iDFamille;

    @Column(name = "`TotalTpsPrÃ©sence`")
    private Double totalTpsPrSence;

    @Column(name = "TpsUnitaire")
    private Double tpsUnitaire;

    @Column(name = "Rndmtchaine")
    private Double rndmtchaine;

    @Column(name = "IDMachine")
    private Integer iDMachine;

}