package com.ranine.rls.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequest {
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private String userName;
    private String password;

}
