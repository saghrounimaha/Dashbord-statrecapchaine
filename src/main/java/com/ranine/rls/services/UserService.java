package com.ranine.rls.services;

import com.ranine.rls.models._User;
import com.ranine.rls.requests.*;

import java.util.List;

public interface UserService {
    void register(RegisterRequest registerRequest);

    JwtToken Login(LoginRequest loginRequest);

    List<_User> getUser();
    _User getById(int id);
    _User getByUsername(String username);
    void createAdmin(RegisterRequest registerRequest);
    void verifiedToken(VerifiedTokenRequest request);
    void sendToken(String email);
    void resetPassword(ResetPasswordRequest request);

}