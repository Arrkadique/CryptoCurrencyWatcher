package com.arrakdique.cryptocurrencywatcher.controller;

import com.arrakdique.cryptocurrencywatcher.dto.UserRegisterDto;
import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "Create user with currency binding")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created user with parameters username & symbol",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class)) })})
    @PostMapping("/notify")
    public Users createUser(@RequestBody UserRegisterDto userData){
        return usersService.createUser(userData.getUsername(), userData.getSymbol());
    }
}
