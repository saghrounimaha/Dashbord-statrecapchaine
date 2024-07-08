package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SumRendementChaineByChaineRequest {
    private Integer chaine;
    private Double rendementChaine;
}
