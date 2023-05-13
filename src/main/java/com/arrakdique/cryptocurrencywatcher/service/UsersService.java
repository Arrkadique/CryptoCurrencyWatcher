package com.arrakdique.cryptocurrencywatcher.service;

import com.arrakdique.cryptocurrencywatcher.repository.CoinsRepository;
import com.arrakdique.cryptocurrencywatcher.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
}
