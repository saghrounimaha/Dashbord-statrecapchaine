package com.company.services;

import com.company.Requests.*;
import com.company.models._User;

import java.util.List;
import java.util.Map;

public interface UserService {

    void register(RegisterRequest registerRequest);

    JwtToken login(LoginRequest loginRequest);

    List<_User> getAll();

    _User getById(long id);

    _User getByUsername(String username);

    void disableEnableUser(long id);

    void updateUser(String username, _User user);

    void sendToken(String email);

    void verifToken(VerifTokenRequest request);

    void reset(ResetPasswordRequest resuest);

    void createAdmin(RegisterRequest registerRequest);

}
