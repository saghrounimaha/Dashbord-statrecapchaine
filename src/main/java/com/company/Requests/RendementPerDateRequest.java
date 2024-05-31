package com.company.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendementPerDateRequest {
    private Date year;
    private Date month;
    private LocalDate date;
    private Double totalRendement;

}
