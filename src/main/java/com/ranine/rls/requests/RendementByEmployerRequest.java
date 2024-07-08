package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendementByEmployerRequest {
    private Integer employerId;
    private Double sumRendement;


}
