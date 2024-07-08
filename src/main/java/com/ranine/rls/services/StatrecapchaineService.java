package com.ranine.rls.services;

import com.ranine.rls.models.Statrecapchaine;
import com.ranine.rls.requests.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StatrecapchaineService {
    List<Statrecapchaine> findAll();
    Statrecapchaine findById(Integer id);
    Statrecapchaine create(Statrecapchaine statrecapchaine);
    Statrecapchaine update(Integer id, Statrecapchaine updateStatrecapchaine);
    void delete(Integer id);
    Double getAvgRendement();
    Double getTotalProduction();
    Integer getTotalEmployee();
    List<TempsTravailParEmployeRequest>  calculateSumTempsTravailParEmploye();
    List<RendementByEmployerRequest> calculateSumRendementByEmployer();
    Map<Integer, Long>  calculateSumQuantiteProduiteParEmploye();
    List<PerformanceParFamilleRequest> getPerformanceParFamille();
    List<MonthlyRendementRequest> getMonthlyRendement();
    List<SumQuantityByChaineMontageRequest> calculateSumQuantityByChaineMontage();
    List< SumRendementChaineByChaineRequest>calculateSumRendementChaineByChaine();
    List<YearlyRendementRequest> getYearlyRendement();
    List<DailyRendementRequest> getDailyRendement();
}
