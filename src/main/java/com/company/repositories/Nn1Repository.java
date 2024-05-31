package com.company.repositories;
import com.company.Requests.*;
import com.company.models.Nn1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Nn1Repository  extends JpaRepository<Nn1,Integer> {

    @Query("SELECT SUM(n.quantite) FROM Nn1 n")
    Double getTotalProduction();

    @Query("SELECT AVG(n.rndmt) FROM Nn1 n")
    Double getAverageRendement();

    @Query("SELECT COUNT(DISTINCT n.idEmploye) FROM Nn1 n")
    Long getTotalActiveEmployees();


    @Query("SELECT new com.company.Requests.RendementByEmployer(n.idEmploye, SUM(n.rndmt)) FROM Nn1 n GROUP BY n.idEmploye")
    List<RendementByEmployer> sumRendementByEmployer();

    @Query("SELECT new com.company.Requests.QuantitySumRequest(n.idChaineMontage, SUM(n.quantite)) " +
            "FROM Nn1 n GROUP BY n.idChaineMontage")
    List<QuantitySumRequest> sumQuantityByChaineMontage();

    @Query("SELECT new com.company.Requests.RendementChaineRequest(n.idChaineMontage, SUM(n.rndmtchaine)) " +
            "FROM Nn1 n " +
            "GROUP BY n.idChaineMontage")
    List<RendementChaineRequest> findSumRendementChaineByChaine();

    @Query("SELECT new com.company.Requests.QuantiteProduiteParEmployeRequest( n.idEmploye, SUM(n.quantite)) FROM Nn1 n GROUP BY n.idEmploye")
    List<QuantiteProduiteParEmployeRequest> findQuantiteProduiteParEmploye();

    @Query("SELECT new com.company.Requests.TempsTravailParEmployeRequest(t.idEmploye, SUM(t.totalTpsTravail)) FROM Nn1 t GROUP BY t.idEmploye")
    List<TempsTravailParEmployeRequest> findTempsTravailTotalParEmploye();
}
