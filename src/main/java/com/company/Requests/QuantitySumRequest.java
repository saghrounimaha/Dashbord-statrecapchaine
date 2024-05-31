package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantitySumRequest {
    private Integer idChaineMontage;
    private Long sumQuantite;
}
