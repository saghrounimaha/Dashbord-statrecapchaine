package com.company.services;

import com.company.Requests.QuantiteProduiteParEmployeRequest;
import com.company.Requests.RendementPerDateRequest;
import com.company.Requests.TempsTravailParEmployeRequest;
import com.company.models.Nn1;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Nn1Service {

    List<Nn1> findAll();
    Optional<Nn1> findById(Integer id);
    Nn1 create(Nn1 nn1);
    Nn1 update(Integer id, Nn1 nn1Details);
    void delete(Integer id);
    Map<Integer, Double> calculateSumRendementByEmployer();
    Map<Integer, Long> calculateSumQuantityByChaineMontage();

    Map<Integer,Double> calculateSumRendementChaine();
    Double getTotalProduction();
    Double getAverageRendement();
    Long getTotalActiveEmployees();
    List<QuantiteProduiteParEmployeRequest> getQuantiteProduiteParEmploye();
    List<TempsTravailParEmployeRequest> getTempsTravailTotalParEmploye();
}
