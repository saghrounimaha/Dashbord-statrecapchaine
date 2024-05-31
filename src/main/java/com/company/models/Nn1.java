package com.company.models;

import jakarta.persistence.*;

@Entity
@Table(name = "nn (1)", schema = "chaine", catalog = "")
public class Nn1 {
    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStatRecapChaine")
    private Integer idStatRecapChaine;
    @Basic
    @Column(name = "IDChaineMontage")
    private Integer idChaineMontage;
    @Basic
    @Column(name = "IDEmploye")
    private Integer idEmploye;
    @Basic
    @Column(name = "Quantite")
    private Integer quantite;
    @Basic
    @Column(name = "TotalTpsTravail")
    private Double totalTpsTravail;
    @Basic
    @Column(name = "Date")
    private String date;
    @Basic
    @Column(name = "Rndmt")
    private Double rndmt;
    @Basic
    @Column(name = "IDOperation")
    private Integer idOperation;
    @Basic
    @Column(name = "IDFamille")
    private Integer idFamille;
    @Basic
    @Column(name = "TotalTpsPrÃƒÂ©sence")
    private Integer totalTpsPrÃƒÂSence;
    @Basic
    @Column(name = "TpsUnitaire")
    private Double tpsUnitaire;
    @Basic
    @Column(name = "Rndmtchaine")
    private Double rndmtchaine;
    @Basic
    @Column(name = "IDMachine")
    private Integer idMachine;

    public Integer getIdStatRecapChaine() {
        return idStatRecapChaine;
    }

    public void setIdStatRecapChaine(Integer idStatRecapChaine) {
        this.idStatRecapChaine = idStatRecapChaine;
    }

    public Integer getIdChaineMontage() {
        return idChaineMontage;
    }

    public void setIdChaineMontage(Integer idChaineMontage) {
        this.idChaineMontage = idChaineMontage;
    }

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getTotalTpsTravail() {
        return totalTpsTravail;
    }

    public void setTotalTpsTravail(Double totalTpsTravail) {
        this.totalTpsTravail = totalTpsTravail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRndmt() {
        return rndmt;
    }

    public void setRndmt(Double rndmt) {
        this.rndmt = rndmt;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public Integer getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(Integer idFamille) {
        this.idFamille = idFamille;
    }

    public Integer getTotalTpsPrÃƒÂSence() {
        return totalTpsPrÃƒÂSence;
    }

    public void setTotalTpsPrÃƒÂSence(Integer totalTpsPrÃƒÂSence) {
        this.totalTpsPrÃƒÂSence = totalTpsPrÃƒÂSence;
    }

    public Double getTpsUnitaire() {
        return tpsUnitaire;
    }

    public void setTpsUnitaire(Double tpsUnitaire) {
        this.tpsUnitaire = tpsUnitaire;
    }

    public Double getRndmtchaine() {
        return rndmtchaine;
    }

    public void setRndmtchaine(Double rndmtchaine) {
        this.rndmtchaine = rndmtchaine;
    }

    public Integer getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(Integer idMachine) {
        this.idMachine = idMachine;
    }


}
