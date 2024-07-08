package com.ranine.rls.services;


import com.ranine.rls.requests.ResetPasswordRequest;
import com.ranine.rls.requests.VerifiedTokenRequest;
import org.springframework.http.ResponseEntity;

public interface ResetPasswordService {
    ResponseEntity<?> sendToken(String email);

    ResponseEntity<?> verifiedToken(VerifiedTokenRequest request);

    ResponseEntity<?> resetPassword(ResetPasswordRequest request);
}
