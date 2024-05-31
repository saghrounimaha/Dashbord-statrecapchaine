package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempsTravailParEmployeRequest {
    private Integer idEmploye;
    private Double tempsTravailTotal;
}
