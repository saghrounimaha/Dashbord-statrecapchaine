package com.ranine.rls.controllers;

import com.ranine.rls.requests.ResetPasswordRequest;
import com.ranine.rls.requests.VerifiedTokenRequest;
import com.ranine.rls.services.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reset-password")
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/send-token")
    public ResponseEntity<?> sendResetToken(@RequestParam("email") String email) {
        return resetPasswordService.sendToken(email);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestBody VerifiedTokenRequest request) {
        return resetPasswordService.verifiedToken(request);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        return resetPasswordService.resetPassword(request);
    }
}
