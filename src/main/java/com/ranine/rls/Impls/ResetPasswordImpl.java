package com.ranine.rls.Impls;

import com.ranine.rls.models._Token;
import com.ranine.rls.models._User;
import com.ranine.rls.repositories.TokenRepository;
import com.ranine.rls.repositories.UserRepository;
import com.ranine.rls.requests.ResetPasswordRequest;
import com.ranine.rls.requests.VerifiedTokenRequest;
import com.ranine.rls.services.ResetPasswordService;
import com.ranine.rls.utils.MailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResetPasswordImpl implements ResetPasswordService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    private String generateToken() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 10);
    }

    @Transactional
    @Override
    public ResponseEntity<?> sendToken(String email) {
        Optional<_User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String tokenText = this.generateToken();
            _Token tokenToSave = new _Token();
            tokenToSave.setTokenText(tokenText);
            tokenToSave.setExpiryDate(this.getExpirationDate());
            tokenToSave.setUser(user.get());
            _Token token = tokenRepository.save(tokenToSave);
            try {
                mailService.sendEmail(user.get().getEmail(), "Reset_Password",
                        "Here is your reset token: " + token.getTokenText());
            } catch (MessagingException e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
            var response = new HashMap<String, String>();
            response.put("message", "Token received successfully!");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("Email not found!");
    }

    @Override
    public ResponseEntity<?> verifiedToken(VerifiedTokenRequest request) {
        _User user = userRepository.findByEmail(request.getEmail()).get();
        Date dateNow = new Date(System.currentTimeMillis());
        Date expDateCurrentToken = user.getToken().getExpiryDate();
        int result = dateNow.compareTo(expDateCurrentToken);
        if (result > 0) {
            return ResponseEntity.status(401).body("Token expired!");
        }
        if (!request.getTokenText().equals(user.getToken().getTokenText())) {
            return ResponseEntity.status(401).body("Invalid token!");
        }
        var response = new HashMap<String, String>();
        response.put("message", "Token is valid");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        _User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body("No such user exists!");
        }
        if (request.getNewPassword().equals(request.getConfirmPassword()) &&
                request.getTokenText().equals(user.getToken().getTokenText())) {

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            tokenRepository.deleteById(user.getToken().getId_token());
            var response = new HashMap<String, String>();
            response.put("message", "Password changed successfully!");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Something went wrong!");
    }
}
