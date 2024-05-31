package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantiteProduiteParEmployeRequest {
    private Integer idEmploye;
    private Long quantite;
}
