package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KpiDTO {
    private Double totalProduction;
    private double averageRendement;
    private Integer totalActiveEmployees;
}
