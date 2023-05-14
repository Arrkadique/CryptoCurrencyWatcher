package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/invoke")
    public Users createUser(@RequestBody Map<String, String> userData){
        return usersService.createUser(userData.get("username"), userData.get("symbol"));
    }
}
