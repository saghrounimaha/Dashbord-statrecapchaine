package com.company.controllers;

import com.company.Requests.QuantiteProduiteParEmployeRequest;
import com.company.Requests.RendementChaineRequest;
import com.company.Requests.RendementPerDateRequest;
import com.company.Requests.TempsTravailParEmployeRequest;
import com.company.models.Nn1;
import com.company.services.Nn1Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/nn")
public class Nn1Controller {


    private Nn1Service nn1Service;

    public Nn1Controller(Nn1Service nn1Service) {
        this.nn1Service = nn1Service;
    }

    @GetMapping
    public List<Nn1> getAll() {
        return nn1Service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nn1> getById(@PathVariable Integer id) {
        Optional<Nn1> nn1 = nn1Service.findById(id);
        return nn1.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Nn1> create(@RequestBody Nn1 nn1) {
        Nn1 createdNn1 = nn1Service.create(nn1);
        return new ResponseEntity<>(createdNn1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nn1> update(@PathVariable Integer id, @RequestBody Nn1 nn1Details) {
        try {
            Nn1 updatedNn1 = nn1Service.update(id, nn1Details);
            return ResponseEntity.ok(updatedNn1);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        nn1Service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sum-rendement-by-employer")
    public ResponseEntity<Map<Integer, Double>> getSumRendementByEmployer() {
        Map<Integer, Double> sumRendementByEmployer = nn1Service.calculateSumRendementByEmployer();
        return ResponseEntity.ok(sumRendementByEmployer);
    }

    @GetMapping("/sum-quanitity-by-chaine")
    public ResponseEntity<Map<Integer, Long>> getSumQuantityByChaine() {
        Map<Integer, Long> sumQuantityByChaine = nn1Service.calculateSumQuantityByChaineMontage();
        return ResponseEntity.ok(sumQuantityByChaine);
    }

    @GetMapping("/sum-rendement-chaine")
    public ResponseEntity<Map<Integer, Double>> getSumRendementChaineByChaine() {
        Map<Integer, Double> sumRendChaine = nn1Service.calculateSumRendementChaine();
        return ResponseEntity.ok(sumRendChaine);    }

    @GetMapping("/kpis")
    public ResponseEntity<Map<String, Object>> getKPIs() {
        Map<String, Object> kpis = new HashMap<>();
        kpis.put("totalProduction", nn1Service.getTotalProduction());
        kpis.put("averageRendement", nn1Service.getAverageRendement());
        kpis.put("totalActiveEmployees", nn1Service.getTotalActiveEmployees());
        return ResponseEntity.ok(kpis);
    }
    @GetMapping("/quantity-par-employe")
    public ResponseEntity<List<QuantiteProduiteParEmployeRequest>> getQuantiteProduiteParEmploye() {
        List<QuantiteProduiteParEmployeRequest> quantiteProduiteParEmploye = nn1Service.getQuantiteProduiteParEmploye();
        return ResponseEntity.ok(quantiteProduiteParEmploye);
    }
    @GetMapping("/temps-par-employe")
    public ResponseEntity<List<TempsTravailParEmployeRequest>> getTempsTravailTotalParEmploye() {
        List<TempsTravailParEmployeRequest> tempsTravailParEmployeList = nn1Service.getTempsTravailTotalParEmploye();
        return ResponseEntity.ok(tempsTravailParEmployeList);
    }
}
