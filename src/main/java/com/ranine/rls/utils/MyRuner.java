package com.ranine.rls.utils;

import com.ranine.rls.models._User;
import com.ranine.rls.requests.RegisterRequest;
import com.ranine.rls.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class MyRuner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest user=new RegisterRequest();
        user.setUserName("ranineline");
        user.setEmail("ranineslimane@gmail.com");
        user.setPassword("0000");
        userService.createAdmin(user);
    }

}
