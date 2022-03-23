package com.example.emlakburadaauth.repository;


import com.example.emlakburadaauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
