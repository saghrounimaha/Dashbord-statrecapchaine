package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DailyRendementRequest {

        private String  day;
        private Double rendement;

}
