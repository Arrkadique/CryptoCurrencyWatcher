package com.arrakdique.cryptocurrencywatcher.service;

import com.arrakdique.cryptocurrencywatcher.entity.Users;
import com.arrakdique.cryptocurrencywatcher.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final CoinsService coinsService;

    public Users createUser(String username, String symbol){
        return usersRepository.save(Users.builder()
                        .username(username)
                        .symbol(symbol)
                        .price(coinsService.getCoinBySymbol(symbol).getPrice())
                .build());
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

}
