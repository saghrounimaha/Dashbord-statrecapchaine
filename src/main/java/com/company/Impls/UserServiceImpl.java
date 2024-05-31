package com.company.Impls;


import com.company.Requests.*;
import com.company.models._Role;
import com.company.models._Token;
import com.company.models._User;
import com.company.repositories.Nn1Repository;
import com.company.repositories.TokenRepo;
import com.company.repositories.UserRepo;
import com.company.services.UserService;
import com.company.utils.MailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

        private final UserRepo userRepo;
        private  final PasswordEncoder passwordEncoder;
        private  final AuthenticationManager authenticationManager;
        private  final JwtEncoder jwtEncoder;
        private final TokenRepo tokenRepo;
        private  final MailService mailService;

        @Override
        public void register(RegisterRequest request) {
            if (userRepo.findByEmail(request.getEmail()).isPresent()) {
                throw new IllegalArgumentException("This email is already in use.");
            }
            if (userRepo.findByUsername(request.getUsername()).isPresent()) {
                throw new IllegalArgumentException("This username has been taken.");
            }

            _User user = _User.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .birthDate(request.getBirthDate())
                    .role(_Role.ROLE_USER)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(true)
                    .build();
            userRepo.save(user);
        }

        @Override
        public JwtToken login(LoginRequest loginRequest) {
            try {
                var auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()));
                return new JwtToken(createToken(auth));

            } catch (DisabledException e) {
                throw new IllegalArgumentException("The account has been disabled");
            } catch (BadCredentialsException e) {
                throw new IllegalArgumentException("Incorrect credentials");
            } catch (Exception e) {
                throw new RuntimeException("Incorrect credentials");
            }
        }

        @Override
        public List<_User> getAll() {
            return userRepo.findAll().stream().filter(user -> user.getRole().equals(_Role.ROLE_USER)).toList();
        }

        @Override
        public _User getById(long id) {
            return userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        @Override
        public _User getByUsername(String username) {
            return userRepo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("user not found"));

        }

        @Override
        public void disableEnableUser(long id) {
            _User user = userRepo.findById(id).get();
            user.setEnabled(!user.isEnabled()); // this will disable the account but not delete it from database!
            userRepo.saveAndFlush(user);
        }

        @Override
        @Transactional
        public void updateUser(String username, _User user) {
            _User userToUpdate = userRepo.findByUsername(username)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Optional<_User> user1 = userRepo.findByEmail(user.getEmail());
            if (user1.isPresent() && !user1.get().equals(userToUpdate)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already in use!");
            }
            Optional<_User> user2 = userRepo.findByUsername(user.getUsername());
            if (user2.isPresent() && !user2.get().equals(userToUpdate)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This username is already taken!");
            }
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setFullName(user.getFullName());
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setUsername(user.getUsername());
        }

        @Override
        public void sendToken(String email) {
            Optional<_User> user = userRepo.findByEmail(email);
            if (user.isPresent()) {
                String tokenText = this.generateToken();
                _Token existingToken = tokenRepo.findByUserEmail(email);
                if (existingToken != null) {
                    tokenRepo.deleteById(existingToken.getId_token());
                }
                _Token tokenToSave = new _Token();
                tokenToSave.setTokenText(tokenText);
                tokenToSave.setExpiryDate(this.getExpirationDate());
                tokenToSave.setUser(user.get());
                _Token token = tokenRepo.save(tokenToSave);
                try {
                    mailService.sendEmail(user.get().getEmail(), "Reset_Password",
                            "Here is your reset token: " + token.getTokenText());
                } catch (MessagingException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user with provided email");
            }
        }

        @Override
        public void verifToken(VerifTokenRequest request) {
            Date dateNow = new Date(System.currentTimeMillis());
            _Token token = tokenRepo.findByUserEmail(request.getEmail());
            Date expDateCurrentToken = token.getExpiryDate();
            int result = dateNow.compareTo(expDateCurrentToken);
            if (result > 0) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The link has expired.");
            }
            if (!request.getToken().equals(token.getTokenText())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong token provided");
            }
        }

        @Override
        public void reset(ResetPasswordRequest request) {
            _User user = userRepo.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("there is no user with this email!"));
            _Token token = tokenRepo.findByUserEmail(request.getEmail());

            if (request.getNewPassword().equals(request.getConfirmPassword()) &&
                    request.getToken().equals(token.getTokenText())) {

                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                tokenRepo.deleteById(token.getId_token());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
            }
        }

        private String createToken(Authentication authentication) {
            var claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(60 * 300))
                    .subject(authentication.getName())
                    .claim("scope", createScope(authentication))
                    .build();

            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);
            return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        }

        private String createScope(Authentication authentication) {
            return authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(" "));
        }

        private String generateToken() {
            return UUID.randomUUID().toString().substring(0, 8);
        }

        private Date getExpirationDate() {
            return new Date(System.currentTimeMillis() + 1000 * 60 * 10);
        }

        @Override
        public void createAdmin(RegisterRequest registerRequest) {
            if (userRepo.findByUsername(registerRequest.getUsername()).isEmpty()
                    && userRepo.findByEmail(registerRequest.getEmail()).isEmpty()) {
                _User user = _User.builder()
                        .username(registerRequest.getUsername())
                        .email(registerRequest.getEmail())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .isEnabled(true)
                        .role(_Role.ROLE_ADMIN)
                        .build();
                userRepo.save(user);
            }
        }

    }


