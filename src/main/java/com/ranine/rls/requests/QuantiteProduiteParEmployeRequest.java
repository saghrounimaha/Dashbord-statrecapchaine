package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantiteProduiteParEmployeRequest {
    private Integer idemployer;
    private Long quantite;
}
