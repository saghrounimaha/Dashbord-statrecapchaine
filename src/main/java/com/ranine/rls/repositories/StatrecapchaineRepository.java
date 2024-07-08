package com.ranine.rls.repositories;

import com.ranine.rls.models.Statrecapchaine;
import com.ranine.rls.requests.*;
import org.hibernate.sql.ast.tree.expression.Distinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatrecapchaineRepository extends JpaRepository<Statrecapchaine,Integer> {

    @Query("select AVG (stat.rndmt)from Statrecapchaine stat")
    Double getAvgRendement();
    @Query("select SUM (stat.quantite)from Statrecapchaine stat")
    Double getTotalProduction();
    @Query("select COUNT (Distinct stat.iDEmploye)from Statrecapchaine stat")
    Integer getTotalEmployee();

    @Query("SELECT new com.ranine.rls.requests.RendementByEmployerRequest(n.iDEmploye, SUM(n.rndmt)) FROM Statrecapchaine n GROUP BY n.iDEmploye")
    List<RendementByEmployerRequest> sumRendementByEmployer();

    @Query("SELECT new com.ranine.rls.requests.SumQuantityByChaineMontageRequest(n.iDChaineMontage, SUM(n.quantite)) FROM Statrecapchaine n GROUP BY n.iDChaineMontage")
    List<SumQuantityByChaineMontageRequest> sumQuantityBychaineMontage();

    @Query("SELECT new com.ranine.rls.requests.SumRendementChaineByChaineRequest(n.iDChaineMontage, SUM(n.rndmtchaine)) FROM Statrecapchaine n GROUP BY n.iDChaineMontage")
    List<SumRendementChaineByChaineRequest> sumRendementChaineByChaine();

    @Query("SELECT new com.ranine.rls.requests.QuantiteProduiteParEmployeRequest(n.iDEmploye, SUM(n.quantite)) FROM Statrecapchaine n GROUP BY n.iDEmploye")
    List<QuantiteProduiteParEmployeRequest> sumQuantiteProduiteParEmploye();

    @Query("SELECT new com.ranine.rls.requests.TempsTravailParEmployeRequest(n.iDEmploye, SUM(n.totalTpsTravail)) FROM Statrecapchaine n GROUP BY n.iDEmploye")
    List<TempsTravailParEmployeRequest> sumTempsTravailParEmploye();

    @Query("SELECT new com.ranine.rls.requests.PerformanceParFamilleRequest(n.iDFamille, AVG (n.rndmt),sum (n.quantite)) FROM Statrecapchaine n GROUP BY n.iDFamille")
    List<PerformanceParFamilleRequest> getPerformanceParFamille();




   /* @Query("SELECT new com.ranine.rls.requests.MonthlyRendementRequest(" +
            "SUBSTRING(CAST(n.date AS string), 9, 2), " +  // Day
            "SUBSTRING(CAST(n.date AS string), 6, 2), " +  // Month
            "SUBSTRING(CAST(n.date AS string), 1, 4), " +  // Year
            "AVG(n.rndmt)) " +
            "FROM Statrecapchaine n " +
            "GROUP BY SUBSTRING(CAST(n.date AS string), 1, 4), " +
            "SUBSTRING(CAST(n.date AS string), 6, 2), " +
            "SUBSTRING(CAST(n.date AS string), 9, 2) " +
            "ORDER BY SUBSTRING(CAST(n.date AS string), 1, 4), " +
            "SUBSTRING(CAST(n.date AS string), 6, 2), " +
            "SUBSTRING(CAST(n.date AS string), 9, 2)")
    List<MonthlyRendementRequest> getMonthlyRendement();*/

    @Query("SELECT new com.ranine.rls.requests.MonthlyRendementRequest(" +
            "SUBSTRING(CAST(n.date AS string), 6, 2), " +  // Month
            "AVG(n.rndmt)) " +
            "FROM Statrecapchaine n " +
            "GROUP BY SUBSTRING(CAST(n.date AS string), 6, 2) " +
            "ORDER BY SUBSTRING(CAST(n.date AS string), 6, 2)")
    List<MonthlyRendementRequest> getMonthlyRendement();


    @Query("SELECT new com.ranine.rls.requests.YearlyRendementRequest(" +
            "SUBSTRING(CAST(n.date AS string), 1, 4), " +  // Year
            "AVG(n.rndmt)) " +
            "FROM Statrecapchaine n " +
            "GROUP BY SUBSTRING(CAST(n.date AS string), 1, 4) " +
            "ORDER BY SUBSTRING(CAST(n.date AS string), 1, 4)")
    List<YearlyRendementRequest> getYearlyRendement();


    @Query("SELECT new com.ranine.rls.requests.DailyRendementRequest(" +
            "SUBSTRING(CAST(n.date AS string), 9, 2), " +  // Day
            "AVG(n.rndmt)) " +
            "FROM Statrecapchaine n " +
            "GROUP BY SUBSTRING(CAST(n.date AS string), 9, 2) " +
            "ORDER BY SUBSTRING(CAST(n.date AS string), 9, 2)")
    List<DailyRendementRequest> getDailyRendement();





}
