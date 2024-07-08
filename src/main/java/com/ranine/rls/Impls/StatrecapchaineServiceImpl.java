package com.ranine.rls.Impls;

import com.ranine.rls.models.Statrecapchaine;
import com.ranine.rls.repositories.StatrecapchaineRepository;
import com.ranine.rls.requests.*;
import com.ranine.rls.services.StatrecapchaineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StatrecapchaineServiceImpl implements StatrecapchaineService {
    @Autowired
    private StatrecapchaineRepository statrecapchaineRepository;

    @Override
    public List<Statrecapchaine> findAll() {
        return statrecapchaineRepository.findAll();
    }

    @Override
    public Statrecapchaine findById(Integer id) {
        return statrecapchaineRepository.findById(id).get();
    }

    @Override
    public Statrecapchaine create(Statrecapchaine nn1) {
        return statrecapchaineRepository.save(nn1);
    }

    @Override
    public Statrecapchaine update(Integer id, Statrecapchaine nn1Details) {
        Optional<Statrecapchaine> nn1Optional = statrecapchaineRepository.findById(id);
        if (nn1Optional.isPresent()) {
            Statrecapchaine nn1 = nn1Optional.get();
            // Update the fields as needed
            nn1.setIDChaineMontage(nn1Details.getIDChaineMontage());
            nn1.setIDEmploye(nn1Details.getIDEmploye());
            nn1.setQuantite(nn1Details.getQuantite());
            nn1.setTotalTpsTravail(nn1Details.getTotalTpsTravail());
            nn1.setDate(nn1Details.getDate());
            nn1.setRndmt(nn1Details.getRndmt());
            nn1.setIDOperation(nn1Details.getIDOperation());
            nn1.setIDFamille(nn1Details.getIDFamille());
            nn1.setTpsUnitaire(nn1Details.getTpsUnitaire());
            nn1.setRndmtchaine(nn1Details.getRndmtchaine());
            nn1.setIDMachine(nn1Details.getIDMachine());

            return statrecapchaineRepository.save(nn1);
        } else {
            throw new IllegalArgumentException("Nn1 with id " + id + " not found");
        }
    }

    @Override
    public void delete(Integer id) {

        statrecapchaineRepository.deleteById(id);
    }

    @Override
    public Double getAvgRendement() {
        return statrecapchaineRepository.getAvgRendement();
    }

    @Override
    public Double getTotalProduction() {
        return statrecapchaineRepository.getTotalProduction();
    }

    @Override
    public Integer getTotalEmployee() {
        return statrecapchaineRepository.getTotalEmployee();
    }

    @Override
    public List<RendementByEmployerRequest> calculateSumRendementByEmployer() {
        return statrecapchaineRepository.sumRendementByEmployer();
    }

    @Override
    public List<SumQuantityByChaineMontageRequest> calculateSumQuantityByChaineMontage() {
        return statrecapchaineRepository.sumQuantityBychaineMontage();
    }

    @Override
    public  List< SumRendementChaineByChaineRequest>calculateSumRendementChaineByChaine() {
        return statrecapchaineRepository.sumRendementChaineByChaine();
    }

    @Override
    public List<YearlyRendementRequest> getYearlyRendement() {
        return statrecapchaineRepository.getYearlyRendement();
    }

    @Override
    public List<DailyRendementRequest> getDailyRendement() {
        return statrecapchaineRepository.getDailyRendement();
    }

    @Override
    public  Map<Integer, Long>  calculateSumQuantiteProduiteParEmploye() {
        List<QuantiteProduiteParEmployeRequest> result = statrecapchaineRepository.sumQuantiteProduiteParEmploye();
        Map<Integer, Long> sumQuantiteProduiteParEmploye = new HashMap<>();
        for (QuantiteProduiteParEmployeRequest dto : result) {

            sumQuantiteProduiteParEmploye.put(dto.getIdemployer(), dto.getQuantite());
        }
        return sumQuantiteProduiteParEmploye;
    }

    @Override
    public  List<TempsTravailParEmployeRequest>  calculateSumTempsTravailParEmploye() {
        return statrecapchaineRepository.sumTempsTravailParEmploye();
    }




    @Override
    public List<PerformanceParFamilleRequest> getPerformanceParFamille(){
        return statrecapchaineRepository.getPerformanceParFamille();
    }

    @Override
    public List<MonthlyRendementRequest> getMonthlyRendement() {
        return statrecapchaineRepository.getMonthlyRendement();
    }



}


