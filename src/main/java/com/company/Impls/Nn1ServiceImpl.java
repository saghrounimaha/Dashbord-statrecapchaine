package com.company.Impls;

import com.company.Requests.*;
import com.company.models.Nn1;
import com.company.repositories.Nn1Repository;
import com.company.services.Nn1Service;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Nn1ServiceImpl implements Nn1Service {
    private Nn1Repository nn1Repository;

    public Nn1ServiceImpl(Nn1Repository nn1Repository) {
        this.nn1Repository = nn1Repository;
    }

    @Override
    public List<Nn1> findAll() {
        return nn1Repository.findAll();
    }
    @Override
    public Optional<Nn1> findById(Integer id) {
        return nn1Repository.findById(id);
    }

    @Override
    public Nn1 create(Nn1 nn1) {
        return nn1Repository.save(nn1);
    }

    @Override
    public void delete(Integer id) {
        nn1Repository.deleteById(id);
    }

    @Override
    public Nn1 update(Integer id, Nn1 nn1Details) {
        Optional<Nn1> nn1Optional = nn1Repository.findById(id);
        if (nn1Optional.isPresent()) {
            Nn1 nn1 = nn1Optional.get();
            // Update the fields as needed
            nn1.setIdChaineMontage(nn1Details.getIdChaineMontage());
            nn1.setIdEmploye(nn1Details.getIdEmploye());
            nn1.setQuantite(nn1Details.getQuantite());
            nn1.setTotalTpsTravail(nn1Details.getTotalTpsTravail());
            nn1.setDate(nn1Details.getDate());
            nn1.setRndmt(nn1Details.getRndmt());
            nn1.setIdOperation(nn1Details.getIdOperation());
            nn1.setIdFamille(nn1Details.getIdFamille());
            nn1.setTpsUnitaire(nn1Details.getTpsUnitaire());
            nn1.setRndmtchaine(nn1Details.getRndmtchaine());
            nn1.setIdMachine(nn1Details.getIdMachine());

            return nn1Repository.save(nn1);
        } else {
            throw new IllegalArgumentException("Nn1 with id " + id + " not found");
        }
    }


    @Override
    public Map<Integer, Double> calculateSumRendementByEmployer() {
        List<RendementByEmployer> result = nn1Repository.sumRendementByEmployer();
        Map<Integer, Double> sumRendementByEmployer = new HashMap<>();
        for (RendementByEmployer dto : result) {

            sumRendementByEmployer.put(dto.getIdEmploye(), dto.getSumRendement());
        }
        return sumRendementByEmployer;
    }
    @Override
    public Map<Integer, Long> calculateSumQuantityByChaineMontage() {
        List<QuantitySumRequest> result = nn1Repository.sumQuantityByChaineMontage();
        Map<Integer, Long> sumQuantityByChaineMontage = new HashMap<>();
        for (QuantitySumRequest dto : result) {
            sumQuantityByChaineMontage.put(dto.getIdChaineMontage(), dto.getSumQuantite());
        }
        return sumQuantityByChaineMontage;
    }
    @Override
    public Map<Integer ,Double> calculateSumRendementChaine(){
         List<RendementChaineRequest> result= nn1Repository.findSumRendementChaineByChaine();
         Map<Integer,Double> sumRandementChaine=new HashMap<>();
        for (RendementChaineRequest dto : result) {
            sumRandementChaine.put(dto.getIdChaineMontage(),dto.getTotalRendementChaine());
        }
         return sumRandementChaine;
    }

    @Override
    public Double getTotalProduction() {
        return nn1Repository.getTotalProduction();
    }

    @Override
    public Double getAverageRendement() {
        return nn1Repository.getAverageRendement();
    }

    @Override
    public Long getTotalActiveEmployees() {
        return nn1Repository.getTotalActiveEmployees();
    }
    @Override
    public List<QuantiteProduiteParEmployeRequest> getQuantiteProduiteParEmploye() {
        return nn1Repository.findQuantiteProduiteParEmploye();
    }
    @Override
    public List<TempsTravailParEmployeRequest> getTempsTravailTotalParEmploye() {
        return nn1Repository.findTempsTravailTotalParEmploye();
    }
}
