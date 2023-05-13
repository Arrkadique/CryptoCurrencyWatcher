package com.arrakdique.cryptocurrencywatcher.repository;

import com.arrakdique.cryptocurrencywatcher.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
