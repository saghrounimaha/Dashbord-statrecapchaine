package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceParFamilleRequest {
    private Integer idFamille;
    private Double avgRendement;
    private Long sumQuantite;

}
