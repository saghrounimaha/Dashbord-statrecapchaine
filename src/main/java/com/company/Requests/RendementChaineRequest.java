package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendementChaineRequest {
    private Integer idChaineMontage;
    private Double totalRendementChaine;
}
