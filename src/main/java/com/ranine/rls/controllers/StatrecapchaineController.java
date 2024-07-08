package com.ranine.rls.controllers;

import com.ranine.rls.Impls.StatrecapchaineServiceImpl;
import com.ranine.rls.models.Statrecapchaine;
import com.ranine.rls.repositories.StatrecapchaineRepository;
import com.ranine.rls.requests.*;
import com.ranine.rls.services.StatrecapchaineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/statrecapchaine")
public class StatrecapchaineController {
    @Autowired
    private StatrecapchaineService statrecapchaineService;
    @Autowired
    private StatrecapchaineRepository statrecapchaineRepository;
    @Autowired
    private StatrecapchaineServiceImpl statrecapchaineServiceImpl;

    @GetMapping
    public ResponseEntity<List<Statrecapchaine>> findAll() {
        List<Statrecapchaine> statrecapchaines = statrecapchaineService.findAll();
        return ResponseEntity.ok(statrecapchaines);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Statrecapchaine statrecapchaine = statrecapchaineService.findById(id);
            return ResponseEntity.ok(statrecapchaine);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Statrecapchaine> create(@RequestBody Statrecapchaine statrecapchaine){

            Statrecapchaine stat = statrecapchaineService.create(statrecapchaine);
         return new ResponseEntity<>(stat,HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Statrecapchaine> update(@PathVariable Integer id, @RequestBody Statrecapchaine statrecapchaine){
        try{
            Statrecapchaine stat=statrecapchaineService.update(id,statrecapchaine);
            return new ResponseEntity<>(stat,HttpStatus.OK);
        }
        catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id ){
        try {
            statrecapchaineService.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        }

    @GetMapping("/avgrendement")
    public Double getavgrendement() {
        return statrecapchaineService.getAvgRendement();
    }

    @GetMapping("/getTotalProduction")
    public Double getTotalProduction() {
        return statrecapchaineService.getTotalProduction();
    }

    @GetMapping("/getTotalEmployee")
    public Integer getTotalEmployee() {
        return statrecapchaineService.getTotalEmployee();
    }

    @GetMapping("/calculateSumRendementByEmployer")
    public List<RendementByEmployerRequest> calculateSumRendementByEmployer() {
        return statrecapchaineService.calculateSumRendementByEmployer();
    }

    @GetMapping("/sum-quantity-by-chaine-montage")
    public ResponseEntity<List<SumQuantityByChaineMontageRequest>> getSumQuantityByChaineMontage() {
        List<SumQuantityByChaineMontageRequest> sumQuantityByChaineMontage = statrecapchaineService.calculateSumQuantityByChaineMontage();
        return ResponseEntity.ok(sumQuantityByChaineMontage);
    }

    @GetMapping("/calculateSumRendementChaineByChaine")
    public  List<SumRendementChaineByChaineRequest> calculateSumRendementChaineByChaine() {
        return statrecapchaineService.calculateSumRendementChaineByChaine();
    }

    @GetMapping("/calculateSumQuantiteProduiteParEmploye")
    public  Map<Integer, Long>  calculateSumQuantiteProduiteParEmploye() {
        return statrecapchaineService.calculateSumQuantiteProduiteParEmploye();
    }

    @GetMapping("/calculateSumTempsTravailParEmploye")
    public  List<TempsTravailParEmployeRequest>  calculateSumTempsTravailParEmploye() {
        return statrecapchaineService.calculateSumTempsTravailParEmploye();
    }

    @GetMapping("/getPerformanceParFamille")
    public List<PerformanceParFamilleRequest> getPerformanceParFamille() {
        return statrecapchaineService.getPerformanceParFamille();
    }

    @GetMapping("/getMonthlyRendement")
    public List<MonthlyRendementRequest> getMonthlyRendement() {
        return statrecapchaineService.getMonthlyRendement();

    }

    @GetMapping("/getYearlyRendement")
    public List<YearlyRendementRequest> getYearlyRendement() {
        return statrecapchaineService.getYearlyRendement();

    }

    @GetMapping("/getDailyRendement")
    public List<DailyRendementRequest> getDailyRendement() {
        return statrecapchaineService.getDailyRendement();

    }

    @GetMapping("/kpi")
    public ResponseEntity<KpiDTO> getKPIs() {
        KpiDTO kpiDTO = new KpiDTO();
        kpiDTO.setTotalProduction(statrecapchaineService.getTotalProduction());
        kpiDTO.setAverageRendement(statrecapchaineService.getAvgRendement());
        kpiDTO.setTotalActiveEmployees(statrecapchaineService.getTotalEmployee());
        return ResponseEntity.ok(kpiDTO);
    }





}

