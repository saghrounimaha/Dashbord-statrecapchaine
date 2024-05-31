package com.company.utils;

import com.company.Requests.RegisterRequest;
import com.company.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest user = new RegisterRequest();
        user.setUsername("admin");
        user.setEmail("aymen.omri@istic.ucar.tn");
        user.setPassword("123456789");

        userService.createAdmin(user);
    }

}
