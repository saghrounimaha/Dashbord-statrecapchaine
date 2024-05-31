package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RendementByEmployer {
    private Integer idEmploye;
    private Double sumRendement;

}
